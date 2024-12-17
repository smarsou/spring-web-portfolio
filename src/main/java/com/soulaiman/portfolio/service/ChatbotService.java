package com.soulaiman.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.soulaiman.portfolio.repository.ChatbotProxy;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotProxy chatbotProxy;

    // In-memory storage: IP -> [Request count, Date]
    private final Map<String, IpRequestInfo> ipRequestMap = new HashMap<>();

    private static final int MAX_REQUESTS_PER_DAY = 2;

    public synchronized String sendRequest(JsonNode json, String ipAddress) {
        LocalDate today = LocalDate.now();

        // Get or initialize request info for the IP
        IpRequestInfo info = ipRequestMap.getOrDefault(ipAddress, new IpRequestInfo(0, today));

        // Reset count if it's a new day
        if (!info.date.equals(today)) {
            info.count = 0;
            info.date = today;
        }

        // Check the request limit
        if (info.count >= MAX_REQUESTS_PER_DAY) {
            System.out.println("Request exceeded for ip : " + ipAddress);
            return "!!ERROR!!Request limit exceeded for today. Come back tomorrow ! ";
        }

        // Increment the count and update the map
        info.count++;
        ipRequestMap.put(ipAddress, info);

        // Process the request
        return chatbotProxy.sendRequest(json);
    }

    // Helper class to store request count and date
    private static class IpRequestInfo {
        int count;
        LocalDate date;

        IpRequestInfo(int count, LocalDate date) {
            this.count = count;
            this.date = date;
        }
    }
}
