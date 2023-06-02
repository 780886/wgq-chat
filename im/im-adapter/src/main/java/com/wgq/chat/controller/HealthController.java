package com.wgq.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {
    @RequestMapping("health")
    public String health() {
        return "ok";
    }
}
