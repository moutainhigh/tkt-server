package com.mtl.cypw.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-17 15:31
 */
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/test")
    public String getDate() {
        log.debug("hello world spring boot!!!");
        return "Hello world";
    }

    @GetMapping({"/", "/index", "/index.html"})
    public String index() {
        return "welcome repository[" + System.currentTimeMillis() + "]";
    }

}
