package com.vftour.study.web.vo;

import lombok.Data;

import java.util.List;

/**
 * AppForm
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/8 11:58
 * @since 1.0
 */
@Data
public class AppForm {
    private Long projectTaskId;

    private List<Long> ids;
}
