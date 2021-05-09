package com.example.notification;

import com.example.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ConsumerChannel {
	
	public void sendMessage(String userName, Message message) throws JsonProcessingException;

}
