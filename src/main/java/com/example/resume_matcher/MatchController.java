package com.example.resume_matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private AIService aiService;

    @PostMapping("/match")
    public String match(@RequestBody Map<String, String> payload) {
        String resume = payload.get("resume");
        String job = payload.get("job");

        return aiService.getMatchScore(resume, job);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}