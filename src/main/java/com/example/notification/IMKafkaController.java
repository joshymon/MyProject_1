package com.example.notification;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.IMTopicConsumer;

@RestController	
public class IMKafkaController {

	private IMTopicConsumer imTopicConsumer;
	private IMKafkaUtils kafkaUtil;
	
    public IMKafkaController(IMTopicConsumer imTopicConsumer, IMKafkaUtils utils) {
        this.imTopicConsumer = imTopicConsumer;
        this.kafkaUtil = utils;
    }
	
	 @GetMapping("/kafka/messages")
	 public List<String> getMessages() {
        return imTopicConsumer.getMessages();
	 }
	 
	 @GetMapping("/kafka/topics")
	 public List<String> getTopics() throws InterruptedException, ExecutionException {
        return kafkaUtil.getTopicsList();
	 }
}

