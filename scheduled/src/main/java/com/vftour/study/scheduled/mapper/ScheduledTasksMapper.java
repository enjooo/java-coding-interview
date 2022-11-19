package com.vftour.study.scheduled.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vftour.study.scheduled.model.ScheduledTasks;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ScheduledTasksMapper
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 18:21
 * @since 1.0
 */
public interface ScheduledTasksMapper extends BaseMapper<ScheduledTasks> {

    List<ScheduledTasks> selectTaskPage(IPage<ScheduledTasks> page, @Param("name") String name);
}
