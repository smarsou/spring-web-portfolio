package com.soulaiman.portfolio.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class ChatbotProxy {

    @Value("${chatbot.domain}")
    private String chatbotApiDomain;

    public String sendRequest(JsonNode content){
        
        String postProjectUrl = this.chatbotApiDomain + "/api/gen";
        
        RestTemplate restTemplate = new RestTemplate();
        
        // Create headers and set Content-Type to application/json
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create HttpEntity with headers and content
        HttpEntity<JsonNode> request = new HttpEntity<JsonNode>(content, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            postProjectUrl,
            HttpMethod.POST,
            request,
            String.class
        );
        
        System.out.println("Create Chatbot call: " + response.getStatusCode().toString());
        
        return response.getBody();
    }

}
