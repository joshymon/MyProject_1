package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageSenderSlack {
    
    public void sendMessage(String userName, Message message) throws JsonProcessingException {
    	
    	String HOOKS_URL = "https://hooks.slack.com/services/%s";
        Map<String, String> USER_TO_CHANNEL_WEBHOOK = new HashMap<>();
        USER_TO_CHANNEL_WEBHOOK.put("IM", "TENPG8UUU/B0217FCM8KF/qlCLACBg3bUqC10YrPuyUSVT");
        
        String userChannelId = USER_TO_CHANNEL_WEBHOOK.get(userName);
        String userWebhookUrl = String.format(HOOKS_URL, userChannelId);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson = objectMapper.writeValueAsString(message);
        HttpEntity<String> entity = new HttpEntity<>(messageJson, headers);
        restTemplate.exchange(userWebhookUrl, HttpMethod.POST, entity, String.class);
    }
}
