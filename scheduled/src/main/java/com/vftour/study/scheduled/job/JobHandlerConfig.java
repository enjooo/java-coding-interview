package com.vftour.study.scheduled.job;

import java.util.HashMap;
import java.util.Map;

/**
 * JobHandler
 *
 * @author 东东 | d@tke.store
 * @date 2022/8/18 14:24
 * @since 1.0
 */
public class JobHandlerConfig {

    private static final Map<String, String> config = new HashMap<>();

    static {
        config.put("项目自动创建", ProjectAutoCreateJob.class.getName());
    }

    public static Map<String, String> getHandler() {
        return config;
    }
}
