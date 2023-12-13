package com.example.demo.controller;

import com.example.demo.model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InfoController {

    @Value("${server.label}")
    String label;

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/api/info")
    List<Info> info(@RequestHeader HttpHeaders headers) {
        List<Info> result = new ArrayList<>();
        result.add(new Info("Backend", "Spring Boot"));
        result.add(new Info("Backend-Container", label));

        System.out.println(headers);

        List<String> proxy = headers.get("X-Proxy");
        if (proxy != null && proxy.size() > 0) {
            result.add(new Info("Backend-Proxy", String.join(",", proxy)));
        } else {
            result.add(new Info("Backend-Proxy", "None"));
        }

        try {
            List<Info> linesFromDb = jdbcTemplate.query("select name, text from infos", new BeanPropertyRowMapper<>(Info.class));
            result.addAll(linesFromDb);
        } catch (Exception e) {
            result.add(new Info("Database", "error: " + e));
        }

        return result;
    }
}
