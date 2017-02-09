package com.test.eaton.training.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.test.eaton.training.entity.Login;

@Component("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	private final LoginRepository loginRepository;

	public LoginServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public Login getLoginDetails(String userId, String password) {
		return loginRepository.findByUserNameAndPassword(userId, password);
	}

}
