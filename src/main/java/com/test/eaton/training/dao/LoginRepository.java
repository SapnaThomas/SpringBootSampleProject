package com.test.eaton.training.dao;

import org.springframework.data.repository.Repository;

import com.test.eaton.training.entity.Login;

public interface LoginRepository extends Repository<Login, Long> {

	Login findByUserNameAndPassword(String user_name, String password);
}
