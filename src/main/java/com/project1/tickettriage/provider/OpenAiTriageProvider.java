package com.project1.tickettriage.provider;

import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@ConditionalOnProperty(name = "ai.enabled", havingValue = "true")
public class OpenAiTriageProvider implements AiTriageProvider {

    private final RestClient restClient;
    private final String model;

    public OpenAiTriageProvider(@Value("${openai.api.key}") String apiKey,
                                @Value("${openai.model}") String model) {
        this.model = model;
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public TriageResponse analyze(TriageRequest request) {
        String prompt = """
                You are a support ticket triage assistant.
                Classify the ticket into one category from: ACCESS, BILLING, TECHNICAL, FEATURE_REQUEST, INCIDENT, GENERAL.
                Classify priority into one of: LOW, MEDIUM, HIGH.
                Classify suggestedStatus into one of: OPEN, ESCALATED.
                Return strict JSON only with keys: category, priority, suggestedStatus, reasoning.

                Title: %s
                Description: %s
                """.formatted(request.getTitle(), request.getDescription());

        Map<String, Object> payload = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful assistant designed to output JSON."),
                        Map.of("role", "user", "content", prompt)
                ),
                "response_format", Map.of("type", "json_object")
        );

        Map response = restClient.post()
                .uri("/chat/completions")
                .body(payload)
                .retrieve()
                .body(Map.class);

        List choices = (List) response.get("choices");
        Map firstChoice = (Map) choices.get(0);
        Map message = (Map) firstChoice.get("message");
        String content = (String) message.get("content");

        return parseJsonContent(content);
    }

    private TriageResponse parseJsonContent(String content) {
        String normalized = content.replace("{", "")
                .replace("}", "")
                .replace("\"", "");

        String category = "GENERAL";
        String priority = "MEDIUM";
        String suggestedStatus = "OPEN";
        String reasoning = "Parsed AI response.";

        String[] parts = normalized.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":", 2);
            if (keyValue.length < 2) {
                continue;
            }
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if ("category".equalsIgnoreCase(key)) {
                category = value;
            } else if ("priority".equalsIgnoreCase(key)) {
                priority = value;
            } else if ("suggestedStatus".equalsIgnoreCase(key)) {
                suggestedStatus = value;
            } else if ("reasoning".equalsIgnoreCase(key)) {
                reasoning = value;
            }
        }

        return new TriageResponse(category, priority, suggestedStatus, reasoning);
    }
}
