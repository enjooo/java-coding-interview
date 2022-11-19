package com.vftour.study.scheduled.model;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vftour.study.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.quartz.JobDataMap;

/**
 * 定时任务
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 17:36
 * @since 1.0
 */
@Data
@TableName("crq_scheduled_tasks")
@EqualsAndHashCode(callSuper = true)
public class ScheduledTasks extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 执行任务的具体类
     */
    private String jobClass;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 执行时间格式
     */
    private String cron;
    /**
     * 任务参数
     */
    private String params;
    /**
     * 任务描述
     */
    private String remarks;
    /**
     * 状态，0:未启用 1:已启用 2:执行中 4:已暂停 5:恢复中
     */
    private Integer status;

    /**
     * 启动延迟  单位/毫秒
     */
    private Integer delay;

    /**
     * 重复次数
     */
    private Integer repeatCount;

    public JobDataMap buildJobDataMap(String params) {
        JobDataMap result = new JobDataMap();
        result.put("id", this.getId());
        result.put("taskName", this.getTaskName());
        result.put("jobClass", this.getJobClass());
        result.put("jobGroup", this.getJobGroup());
        result.put("cron", this.getCron());
        if (StrUtil.isNotBlank(params)) {
            result.put("params", params);
        } else {
            result.put("params", this.getParams());
        }

        result.put("remarks", this.getRemarks());
        result.put("status", this.getStatus());
        return result;
    }

}
