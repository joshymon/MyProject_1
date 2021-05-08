package com.example.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.IncModel;
import com.example.service.IMService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class IMController {
	
	@Autowired
	private IMService imservice;
	
	@PostMapping(value = "/Incident")
	public ResponseEntity<?> createIncident(@RequestBody(required = true) IncModel in, HttpServletRequest req) throws JsonProcessingException, InterruptedException, ExecutionException {
        return imservice.createIncident(in, req);	
    }
	
	@PutMapping(value = "/Incident")
	public ResponseEntity<?> updateIncident(@RequestBody(required = true) IncModel in, HttpServletRequest req) throws JsonProcessingException {
        return imservice.updateIncident(in, req);	
    }
	
	
}
