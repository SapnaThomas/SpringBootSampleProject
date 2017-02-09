package com.test.eaton.training.dao;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.eaton.training.entity.Attendance;
import com.test.eaton.training.entity.StudentDetails;
import com.test.eaton.training.response.StudentListResponse;


public interface StudentService {
	Page<Object[]> listAllByPage(Pageable pageable);

	// Page<StudentDetails> findByName(String name, Pageable pageable);
	Page<Object[]> findByName(String name, Pageable pageable);

	Page<Object[]> findByRoll(long rollNo, Pageable pageable);

	Page<Object[]> findByClass(String division, Pageable pageable);

	Page<Object[]> findByStatus(String status, Pageable pageable);
	public int updateDetails(String status, int id);

	public Long getCount(String status);
	StudentListResponse fetchStudentDetails(Map<String, String> requestParams);
}
