package com.vftour.study.scheduled.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vftour.study.scheduled.model.ScheduledTasks;

/**
 * IScheduledTasksService
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 18:04
 * @since 1.0
 */
public interface IScheduledTasksService extends IService<ScheduledTasks> {

    /**
     * 任务列表
     *
     * @param page
     * @param name
     * @return
     */
    IPage<ScheduledTasks> getTaskPage(IPage<ScheduledTasks> page, String name);

    /**
     * 添加任务
     *
     * @param task
     * @return
     */
    boolean submit(ScheduledTasks task);

    /**
     * 任务启用
     */
    void enable(Long id);

    /**
     * 任务暂停
     */
    void pause(Long id);

    /**
     * 任务恢复
     */
    void resume(Long id);

    /**
     * 立刻执行一次
     *
     * @param id
     */
    void runOnce(Long id);

    /**
     * 任务详情
     *
     * @param id
     * @return
     */
    ScheduledTasks detail(Long id);

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    boolean delete(Long id);
}
