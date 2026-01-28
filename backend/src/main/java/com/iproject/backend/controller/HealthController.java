package com.iproject.backend.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//(RestController = responsebody + controller
// Controller -> receives http requests

public class HealthController {
    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        // jdbcTemplate = safer  way to convert SQL from JAVA
    }

    @GetMapping("/api/health")
    public String health(){
        return "OK";
    }
    @GetMapping("/")
    public String home(){
        return "Heh Server is running. Try /api/health ";
    }

    @GetMapping("/api/db/ping")
    public Map<String , String> dbPing(){
        Integer one = jdbcTemplate.queryForObject("SELECT 1",Integer.class);
        return Map.of("db", (one != null && one == 1) ? "UP" :"DOWN");
    }
}
