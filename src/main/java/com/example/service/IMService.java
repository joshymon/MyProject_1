package com.example.service;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.IncModel;
import com.example.notification.IMKafkaUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IMService {
	
	private IMKafkaUtils kafkaUtils;
	private String consumerTopic = "IMTopic";
	enum IMStatus
	{
	    NEW, INPROCESS, COMPLETED;
	}
	
    public IMService(IMKafkaUtils utils) {
        this.kafkaUtils = utils;
    }

	public ResponseEntity<?> createIncident(@RequestBody(required = true) IncModel in, HttpServletRequest req) throws JsonProcessingException, InterruptedException, ExecutionException {
		if(!kafkaUtils.getTopicsList().contains(consumerTopic)) {
			kafkaUtils.createTopics(consumerTopic);
		}
		in.setStatus(IMStatus.NEW.toString());
		kafkaUtils.sendMessage(consumerTopic, getMessageString(in));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateIncident(IncModel in, HttpServletRequest req) throws JsonProcessingException {
		in.setStatus(IMStatus.COMPLETED.toString());
		kafkaUtils.sendMessage(consumerTopic, getMessageString(in));
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	private String getMessageString(IncModel in) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(in);
	}
}
