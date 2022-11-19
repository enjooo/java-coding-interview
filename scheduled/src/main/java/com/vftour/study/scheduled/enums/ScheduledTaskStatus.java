package com.vftour.study.scheduled.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ScheduledTaskStatus
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 18:32
 * @since 1.0
 */

@Getter
@AllArgsConstructor
public enum ScheduledTaskStatus {
    PENDING(0, "未启用"),
    ENABLE(1, "已启用"),
    EXECUTING(2, "执行中"),
    COMPLETE(3, "已完成"),
    PAUSE(4, "已暂停"),
    RESUME(5, "恢复中"),
    ;

    private Integer value;

    private String name;
}

