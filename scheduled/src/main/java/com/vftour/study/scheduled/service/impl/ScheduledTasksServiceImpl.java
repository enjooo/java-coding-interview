package com.vftour.study.scheduled.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vftour.study.scheduled.enums.ScheduledTaskStatus;
import com.vftour.study.scheduled.mapper.ScheduledTasksMapper;
import com.vftour.study.scheduled.model.ScheduledTasks;
import com.vftour.study.scheduled.service.IScheduledTasksService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * ScheduledTasksServiceImpl
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 18:06
 * @since 1.0
 */
@Slf4j
@Service
public class ScheduledTasksServiceImpl extends ServiceImpl<ScheduledTasksMapper, ScheduledTasks> implements IScheduledTasksService {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Override
    public IPage<ScheduledTasks> getTaskPage(IPage<ScheduledTasks> page, String name) {
        List<ScheduledTasks> data = baseMapper.selectTaskPage(page, name);
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeySet) {

                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (ScheduledTasks task : data) {
                    for (Trigger trigger : triggers) {
                        if (task.getJobClass().equals(jobKey.getName()) && task.getJobGroup().equals(jobKey.getGroup())) {
                            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                            状态，0:未启用 1:已启用 2:等待 3:已暂停 4:执行中 5:被锁 6:错误 7:完成
                            switch (triggerState) {
                                case NONE:
                                    task.setStatus(2);
                                    break;
                                case COMPLETE:
                                    task.setStatus(7);
                                    break;
                                case PAUSED:
                                    task.setStatus(3);
                                    break;
                                case ERROR:
                                    task.setStatus(6);
                                    break;
                                case BLOCKED:
                                    task.setStatus(5);
                                    break;
                                case NORMAL:
                                    task.setStatus(4);
                                    break;
                                default:
                                    task.setStatus(1);
                            }
                        }
                    }
                }
            }
        } catch (SchedulerException e) {
            log.debug(e.getMessage());
        }
        return page.setRecords(data);
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public boolean submit(ScheduledTasks task) {
        if (task.getId() != null) {
            ScheduledTasks oldTask = baseMapper.selectById(task.getId());
            if (oldTask != null) {

                Scheduler scheduler = schedulerFactory.getScheduler();
                JobKey jobKey = JobKey.jobKey(task.getJobClass(), task.getJobGroup());
                TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
                CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                if (cronTrigger != null) {
                    // 停止触发器
                    scheduler.pauseTrigger(triggerKey);
                    // 移除触发器
                    scheduler.unscheduleJob(triggerKey);
                    // 删除任务
                    scheduler.deleteJob(jobKey);
                }
            }
        }
        task.setStatus(ScheduledTaskStatus.PENDING.getValue());

        return this.saveOrUpdate(task);
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void enable(Long id) {
        ScheduledTasks task = baseMapper.selectOne(Wrappers.<ScheduledTasks>query().lambda()
                .eq(ScheduledTasks::getId, id)
                .eq(ScheduledTasks::getDeleted, false));
        if (task != null) {
            //启用
            task.setStatus(ScheduledTaskStatus.ENABLE.getValue());
            this.updateById(task);

            //构建job信息
            Scheduler scheduler = schedulerFactory.getScheduler();

            Class jobClass = Class.forName(task.getJobClass());
            jobClass.newInstance();

            JobDetail job = JobBuilder.newJob(jobClass).withIdentity(task.getJobClass(),
                    task.getJobGroup())
                    .withDescription(task.getRemarks())
                    .setJobData(task.buildJobDataMap(null))
                    .storeDurably().build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobClass(), task.getJobGroup())
                    .withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        }
    }

    @Override
    @SneakyThrows
    public void pause(Long id) {
        ScheduledTasks task = baseMapper.selectById(id);
        if (task == null) {
            return;
        }
        JobKey key = new JobKey(task.getJobClass(), task.getJobGroup());
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.pauseJob(key);
    }

    @Override
    @SneakyThrows
    public void resume(Long id) {
        ScheduledTasks task = baseMapper.selectById(id);
        if (task == null) {
            return;
        }
        JobKey key = new JobKey(task.getJobClass(), task.getJobGroup());
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.resumeJob(key);
    }

    @Override
    @SneakyThrows
    public void runOnce(Long id) {
        ScheduledTasks task = baseMapper.selectById(id);
        if (task == null) {
            return;
        }
        JobKey key = new JobKey(task.getJobClass(), task.getJobGroup());
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.triggerJob(key);
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        ScheduledTasks task = baseMapper.selectById(id);
        if (task == null) {
            return false;
        }
        task.setStatus(ScheduledTaskStatus.PENDING.getValue());
        task.setDeleted(true);
        baseMapper.updateById(task);

        Scheduler scheduler = schedulerFactory.getScheduler();

        JobKey jobKey = new JobKey(task.getJobClass(), task.getJobGroup());
        TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(jobKey);
        return scheduler.deleteJob(jobKey);
    }

    @Override
    public ScheduledTasks detail(Long id) {
        return baseMapper.selectById(id);
    }
}
