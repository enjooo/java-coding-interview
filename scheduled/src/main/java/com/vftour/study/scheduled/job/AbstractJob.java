package com.vftour.study.scheduled.job;

import com.vftour.study.scheduled.model.ScheduledTasks;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * AbstractJob
 *
 * @author 东东 | d@tke.store
 * @date 2022/8/18 12:02
 * @since 1.0
 */
public abstract class AbstractJob extends QuartzJobBean {

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduledTasks scheduledTasks = convert(jobExecutionContext.getMergedJobDataMap());
        try {
            handle(jobExecutionContext, scheduledTasks);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    public abstract void handle(JobExecutionContext context, ScheduledTasks scheduledTask) throws JobExecutionException;

    private ScheduledTasks convert(JobDataMap map) {
        ScheduledTasks task = new ScheduledTasks();
        task.setId(map.getLong("id"));
        task.setTaskName(map.getString("taskName"));
        task.setJobClass(map.getString("jobClass"));
        task.setJobGroup(map.getString("jobGroup"));
        task.setCron(map.getString("cron"));
        task.setParams(map.getString("params"));
        task.setRemarks(map.getString("remarks"));
        task.setStatus(map.getInt("status"));
        return task;
    }
}
