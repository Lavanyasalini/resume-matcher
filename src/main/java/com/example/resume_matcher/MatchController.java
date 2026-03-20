package com.example.resume_matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private AIService aiService;

    // ✅ Root endpoint (fixes your Railway error page)
    @GetMapping("/")
    public String home() {
        return "Resume Matcher API is running! 🚀 Use /api/match to test.";
    }

    // ✅ Health check (already good)
    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    // ✅ Main API
    @PostMapping("/match")
    public String match(@RequestBody Map<String, String> payload) {
        String resume = payload.get("resume");
        String job = payload.get("job");

        return aiService.getMatchScore(resume, job);
    }
}