package com.example.resume_matcher;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AIService {

    private final String API_KEY = "YOUR_API_KEY";

    public String getMatchScore(String resume, String job) {

        OkHttpClient client = new OkHttpClient();

        String prompt = "Compare this resume: " + resume +
                " with job: " + job +
                ". Give match score out of 100 and missing skills.";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                "{ \"model\": \"gpt-4o-mini\", \"messages\": [{\"role\":\"user\",\"content\":\"" + prompt + "\"}] }"
        );

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            return "Error calling AI API";
        }
    }
}