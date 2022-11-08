package com.vftour.study.web.controller;

import com.vftour.study.web.vo.AppForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IndexController
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/8 11:55
 * @since 1.0
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class IndexController {

    @GetMapping("/offline/data/{projectTaskId}")
    public String get(@PathVariable Long projectTaskId) {
        return "data:" + projectTaskId;
    }

    @PostMapping("/offline/data")
    public String post(@RequestBody List<AppForm> form) {
        return "data:" + form.size();
    }
}
