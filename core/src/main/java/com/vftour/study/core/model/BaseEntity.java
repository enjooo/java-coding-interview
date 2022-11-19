package com.vftour.study.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * BaseEntity
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/19 14:44
 * @since 1.0
 */
@Data
public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "deleted")
    private Boolean deleted;

    @TableField(value = "create_id")
    private Long createId;

    @TableField(value = "create_name")
    private String createName;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_id")
    private Long updateId;

    @TableField(value = "update_name")
    private String updateName;

    @TableField(value = "update_time")
    private Date updateTime;
}
