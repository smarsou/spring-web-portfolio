package com.soulaiman.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.soulaiman.portfolio.repository.ChatbotProxy;

@Service
public class ChatbotService {
    
    @Autowired
    private ChatbotProxy chatbotProxy;

    public String sendRequest(JsonNode json){
        return chatbotProxy.sendRequest(json);
    }

}
