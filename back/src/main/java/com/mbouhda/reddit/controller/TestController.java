package com.mbouhda.reddit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class TestController {

    @GetMapping
    public String foo() {
        return "foo";
    }
}
