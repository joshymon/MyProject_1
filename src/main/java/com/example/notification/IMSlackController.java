package com.example.notification;

import org.springframework.web.bind.annotation.RestController;

import com.example.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class IMSlackController {
	
	 private final ConsumerChannelSlack consumerChannelSlack;

    public IMSlackController() {
         this.consumerChannelSlack = new ConsumerChannelSlack(); 
	}

	@PostMapping(path = "/messages")
	public ResponseEntity<String> sendMessage(@PathVariable String userName, @RequestBody Message message) throws JsonProcessingException {
		try {	
            consumerChannelSlack.sendMessage(userName, message);
            return ResponseEntity.ok().build();
		} catch (JsonProcessingException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
