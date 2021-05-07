package com.example.controller;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification.IMTopicConsumer;

@RestController	
public class IMKafkaController {

	private KafkaTemplate<String, String> template;
	private IMTopicConsumer imTopicConsumer;
	
    public IMKafkaController(KafkaTemplate<String, String> template, IMTopicConsumer imTopicConsumer) {
        this.template = template;
        this.imTopicConsumer = imTopicConsumer;
        
    }
	    
	@GetMapping("/kafka/produce")
	public void produce(@RequestParam String message) {
	    template.send("myTopic", message);
	}
	
	 @GetMapping("/kafka/messages")
	 public List<String> getMessages() {
        return imTopicConsumer.getMessages();
	 }
}

