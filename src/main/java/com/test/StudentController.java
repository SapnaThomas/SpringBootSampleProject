package com.test;


import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.test.eaton.training.dao.LoginService;
import com.test.eaton.training.dao.StudentService;
import com.test.eaton.training.entity.Login;
import com.test.eaton.training.exception.ValidationException;
import com.test.eaton.training.request.LoginRequest;
import com.test.eaton.training.request.UpdateStudentRequest;
import com.test.eaton.training.response.LoginResponse;
import com.test.eaton.training.response.ResponseMessage;
import com.test.eaton.training.response.StudentListResponse;
import com.test.eaton.training.util.CustomErrorType;
import com.test.eaton.training.util.Validator;
import com.test.eaton.training.websocket.SocketHandler;

@RestController
public class StudentController {
	@Autowired
	LoginService loginService;
	@Autowired
	StudentService studentService;

	@Autowired
	Validator validator;
	
	@Autowired
	SocketHandler socketHandler;
	
	public static final String INPUT_VALIDATION_ERRORCODE="input.validation.error" ;
	
	
	//@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/login")
	@PostMapping
	ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Login login = null;
		LoginResponse response = null;
		ResponseMessage message = null;
		ResponseEntity<ResponseMessage> entity = null;
		try {
			if(null!=loginRequest){
			validator.validateLogin(loginRequest);
			}
			login = this.loginService.getLoginDetails(
					loginRequest.getUserName(), loginRequest.getPassword());
			if (null == login) {
				message = new ResponseMessage();
				message.setCode("login.failed");
				message.setMessage("Invalid credentials");
				entity = new ResponseEntity<ResponseMessage>(message,HttpStatus.CONFLICT);
				return entity;
			}
			else{
			response = new LoginResponse();
			BeanUtils.copyProperties(login, response);
			}
		} 
		catch(ValidationException ex){
			message = new ResponseMessage();
			message.setCode(INPUT_VALIDATION_ERRORCODE);
			message.setMessage(ex.getMessage());
			entity = new ResponseEntity<ResponseMessage>(message,HttpStatus.BAD_REQUEST);
			return entity;
		}
		catch (Exception e) {
		
			return new ResponseEntity(new CustomErrorType("Log in Failed"), HttpStatus.NOT_FOUND);

		}

		return ResponseEntity.ok(response);
	}

	@RequestMapping("/getstudentlist")
	ResponseEntity<?> getStudentList(
			@RequestParam Map<String, String> requestParams) {
		ResponseEntity<ResponseMessage> entity = null;
		ResponseMessage message = null;
		StudentListResponse studentResponses = null;
		try {


			studentResponses = studentService
					.fetchStudentDetails(requestParams);
			return ResponseEntity.ok(studentResponses);
		} catch (Exception e) {
			message = new ResponseMessage();
			message.setCode("student.fetch.failed");
			message.setMessage("No Details Found.Please Try Again!! ");
			entity = new ResponseEntity<ResponseMessage>(message,
					HttpStatus.CONFLICT);
			return entity;
		}
	}

	/**
	 * Update student status
	 * 
	 * @param updateStudentRequest
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStudentdetails(
			@RequestBody UpdateStudentRequest updateStudentRequest) {
		ResponseEntity<ResponseMessage> entity = null;
		WebSocketSession session = null;
		
		ResponseMessage message = null;
		try {
			if(null!=updateStudentRequest){
				validator.validateUpdateRequest(updateStudentRequest);
			}
			String status = updateStudentRequest.getStatus();
			int rollNo = Integer.parseInt(updateStudentRequest.getRollNo());
			int attendance=studentService.updateDetails(status, rollNo);
			
			if(attendance!=0){
			message = new ResponseMessage();
			message.setCode("student.update.success");
			message.setMessage("Details updated successfully");
			entity = new ResponseEntity<ResponseMessage>(message,HttpStatus.ACCEPTED);
			socketHandler.handleTextMessage(session, new TextMessage("updated count"));
			return entity;
			}
			else{
				message = new ResponseMessage();
				message.setCode("student.record.notfound");
				message.setMessage("No records found for the given RollNo");
				entity = new ResponseEntity<ResponseMessage>(message,HttpStatus.ACCEPTED);
				return entity;
			}
		} 
		catch(ValidationException ex){
			message = new ResponseMessage();
			message.setCode(INPUT_VALIDATION_ERRORCODE);
			message.setMessage(ex.getMessage());
			entity = new ResponseEntity<ResponseMessage>(message,HttpStatus.BAD_REQUEST);
			return entity;
		}catch (Exception ex) {
			message = new ResponseMessage();
			message.setCode("student.update.failed");
			message.setMessage("Update Failed ");
			entity = new ResponseEntity<ResponseMessage>(message,
					HttpStatus.CONFLICT);
			return entity;
		}
	}
}
