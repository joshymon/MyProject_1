package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.models.Message;
import com.example.service.MessageSenderSlack;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class IMSlackController {
	
	 private final MessageSenderSlack messageSenderSlack;

    public IMSlackController(MessageSenderSlack messageSenderSlack) {
         this.messageSenderSlack = messageSenderSlack; 
	}

	@PostMapping(path = "/messages")
	public ResponseEntity<String> sendMessage(@PathVariable String userName, @RequestBody Message message) throws JsonProcessingException {
		try {	
            messageSenderSlack.sendMessage(userName, message);
            return ResponseEntity.ok().build();
		} catch (JsonProcessingException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
