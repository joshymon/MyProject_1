package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class IMTopicConsumer {
	private final List<String> messages = new ArrayList<>();
	private final String consumerTopic = "IMTopic";
	private final String consumerGroup = "kafka-sandbox";
	private MessageSenderSlack sender;
	private String slackUser = "IM";
	
	public IMTopicConsumer(MessageSenderSlack sender) {
		this.sender = sender;
	}
	
    @KafkaListener(topics = consumerTopic, groupId = consumerGroup)
    public void listen(String message) throws JsonProcessingException {
        synchronized (messages) {
            messages.add(message);
            Message m = new Message();
            m.setText(message);
            sender.sendMessage(slackUser, m);
        }
    }
    public List<String> getMessages() {
        return messages;
    }
}
