package com.vftour.study.scheduled.controller;

import com.vftour.study.core.api.Result;
import com.vftour.study.scheduled.job.JobHandlerConfig;
import com.vftour.study.scheduled.model.ScheduledTasks;
import com.vftour.study.scheduled.service.IScheduledTasksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务接口
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 19:13
 * @since 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/scheduled")
public class ScheduledController {

    private final IScheduledTasksService scheduledTasksService;

    /**
     * 任务job处理器
     *
     * @return
     */
    @GetMapping("/handler")
    public Result<Map<String, String>> jobHandler() {
        return Result.data(JobHandlerConfig.getHandler());
    }

    /**
     * 添加任务
     *
     * @param task
     * @return
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody ScheduledTasks task) {
        return Result.status(scheduledTasksService.submit(task));
    }

    /**
     * 任务启用
     *
     * @param id
     * @return
     */
    @PostMapping("/enable")
    public Result enable(@RequestParam Long id) {
        scheduledTasksService.enable(id);
        return Result.success("操作成功");
    }

    /**
     * 任务暂停
     *
     * @param id
     * @return
     */
    @PostMapping("/pause/{id}")
    public Result pause(@PathVariable Long id) {
        scheduledTasksService.pause(id);
        return Result.success("操作成功");
    }

    /**
     * 任务恢复
     *
     * @param id
     * @return
     */
    @PostMapping("/resume/{id}")
    public Result resume(@PathVariable Long id) {
        scheduledTasksService.resume(id);
        return Result.success("操作成功");
    }

    /**
     * 立刻执行一次
     *
     * @param id
     * @return
     */
    @PostMapping("/runOnce/{id}")
    public Result runOnce(@PathVariable Long id) {
        scheduledTasksService.runOnce(id);
        return Result.success("操作成功");
    }

    /**
     * 移除任务
     *
     * @param id
     * @return
     */
    @PostMapping("/remove")
    public Result remove(@RequestParam Long id) {
        return Result.status(scheduledTasksService.delete(id));
    }

}