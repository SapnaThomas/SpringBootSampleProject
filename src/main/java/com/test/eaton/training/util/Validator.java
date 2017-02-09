package com.test.eaton.training.util;





import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.test.eaton.training.exception.ValidationException;
import com.test.eaton.training.request.LoginRequest;
import com.test.eaton.training.request.UpdateStudentRequest;

@Component
public class Validator {

	public void validateLogin(LoginRequest loginRequest) {
		
			if (StringUtils.isEmpty(loginRequest.getUserName())
					|| StringUtils.isEmpty(loginRequest.getPassword())) {
				throw new ValidationException("username and password is mandatory", null);
			}
	}
	
	public void validateUpdateRequest(UpdateStudentRequest updateStudentRequest) {
		
		if (StringUtils.isEmpty(updateStudentRequest.getRollNo())
				|| StringUtils.isEmpty(updateStudentRequest.getStatus())) {
			throw new ValidationException("Roll No and Status is mandatory", null);
		}
		
		else if(!("Present".equalsIgnoreCase(updateStudentRequest.getStatus()) ||"Absent".equalsIgnoreCase(updateStudentRequest.getStatus())))
		{
			throw new ValidationException("Invalid value in Status field", null);
		}
}

}
