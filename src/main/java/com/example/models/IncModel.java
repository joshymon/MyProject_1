package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncModel {

	@JsonProperty("TicketId")
	int ticketId;
	

	@JsonProperty("Reporter")
	String reporter;
	
	@JsonProperty("Processor")
	String processor;
	
	@JsonProperty("Status")
	String status;
	
	@JsonProperty("Issue_description")
	String issue_description;
	
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssue_description() {
		return issue_description;
	}

	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}

	@Override
	public String toString() {
		return "IncModel [ticketId=" + ticketId + ", reporter=" + reporter + ", processor=" + processor + ", status="
				+ status + ", issue_description=" + issue_description + "]";
	}
		
}
