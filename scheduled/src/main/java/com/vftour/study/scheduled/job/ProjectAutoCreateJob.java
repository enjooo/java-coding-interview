package com.vftour.study.scheduled.job;

import com.vftour.study.scheduled.model.ScheduledTasks;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * 自动生成项目和任务job
 *
 * @author 东东 | d@tke.store
 * @date 2022/8/17 17:37
 * @since 1.0
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class ProjectAutoCreateJob extends AbstractJob {

    @Override
    public void handle(JobExecutionContext context, ScheduledTasks scheduledTask) throws JobExecutionException {
        log.info("自动生成项目和任务job");

        log.info("更新项目任务完成");
    }
}
