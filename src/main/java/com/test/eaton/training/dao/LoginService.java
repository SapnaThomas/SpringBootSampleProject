package com.test.eaton.training.dao;

import com.test.eaton.training.entity.Login;

public interface LoginService {

	public Login getLoginDetails(String userId, String password);
}
