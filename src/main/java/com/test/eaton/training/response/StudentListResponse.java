package com.test.eaton.training.response;

import java.util.List;

public class StudentListResponse {
	
	private List<StudentResponse> studentDetails;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<StudentResponse> getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(List<StudentResponse> studentDetails) {
		this.studentDetails = studentDetails;
	}
	
	
}
