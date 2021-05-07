package com.example.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.IncModel;
import com.example.util.ApiResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IMService {
	
	private KafkaTemplate<String, String> template;
	
    public IMService(KafkaTemplate<String, String> template) {
        this.template = template;
        
    }

	public Response getIncidents() {
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
	}

	public ResponseEntity<?> createIncident(@RequestBody(required = true) IncModel in, HttpServletRequest req) throws JsonProcessingException {
		//send notification on the incident
		in.setStatus("New");
		ObjectMapper m = new ObjectMapper();
		String mStr = m.writeValueAsString(in);
		template.send("myTopic", mStr);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateIncident(IncModel in, HttpServletRequest req) throws JsonProcessingException {
		in.setStatus("Completed");
		ObjectMapper m = new ObjectMapper();
		String mStr = m.writeValueAsString(in);
		template.send("myTopic", mStr);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	

}
