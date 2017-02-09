package com.test.eaton.training.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.test.eaton.training.response.StudentListResponse;
import com.test.eaton.training.response.StudentResponse;
import com.test.eaton.training.util.StudentUtil;

@Component("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentPagingRepository studentPagingRepo;
	private final AttendanceRepository detailsRepository;

	public StudentServiceImpl(StudentPagingRepository studentPagingRepo,
			AttendanceRepository detailsRepository) {
		this.studentPagingRepo = studentPagingRepo;
		this.detailsRepository = detailsRepository;
	}

	@Override
	public Page<Object[]> listAllByPage(Pageable pageable) {
		Timestamp start = StudentUtil.getStartDate();
		Timestamp end = StudentUtil.getEndDate();
		return studentPagingRepo.listAllByPage(start, end,pageable);
	}

	@Override
	public Long getCount(String status) {
		Timestamp start=StudentUtil.getStartDate();
		Timestamp end=StudentUtil.getEndDate();
		
		return detailsRepository.getPresentCount(status,start,end);

	}

	@Override
	public int updateDetails(String status, int rollNo) {
		Timestamp start=StudentUtil.getStartDate();
		Timestamp end=StudentUtil.getEndDate();
		
		int attendance=detailsRepository.setStatus(status, rollNo,start,end);
		return attendance;
	}
	/*
	 * public Page<StudentDetails> findByName(String name,Pageable pageable) {
	 * return studentPagingRepo.findByStudentName(name,pageable); }
	 */
	@Override
	public Page<Object[]> findByName(String name, Pageable pageable) {
		Timestamp start = StudentUtil.getStartDate();
		Timestamp end = StudentUtil.getEndDate();
		return studentPagingRepo.findByStudentName(name,start, end, pageable);
	}

	

	@Override
	public Page<Object[]> findByRoll(long rollNo, Pageable pageable) {
		Timestamp start = StudentUtil.getStartDate();
		Timestamp end = StudentUtil.getEndDate();
		return studentPagingRepo.findById(rollNo,start, end, pageable);
	}

	@Override
	public Page<Object[]> findByClass(String division, Pageable pageable) {
		Timestamp start = StudentUtil.getStartDate();
		Timestamp end = StudentUtil.getEndDate();
		return studentPagingRepo.findByClassDiv(division,start, end, pageable);
	}

	@Override
	public Page<Object[]> findByStatus(String status, Pageable pageable) {
		Timestamp start = StudentUtil.getStartDate();
		Timestamp end = StudentUtil.getEndDate();
		return studentPagingRepo.findByStatus(status,start, end, pageable);
	}

	@Override
	public StudentListResponse fetchStudentDetails(
			Map<String, String> requestParams) {

		StudentResponse studentResponse = null;
		List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
		StudentListResponse studentDetails= new StudentListResponse();
		StudentPageableImpl pageableImpl = new StudentPageableImpl(
				Integer.parseInt(requestParams.get("pageNum")),
				Integer.parseInt(requestParams.get("size")));

		Page<Object[]> page = null;
		if (null != requestParams.get("name")) {
			page = findByName(requestParams.get("name"), pageableImpl);

		} else if (null != requestParams.get("rollNo")) {
			page = findByRoll(Long.parseLong(requestParams.get("rollNo")),
					pageableImpl);

		} else if (null != requestParams.get("class")) {
			page = findByClass(requestParams.get("class"), pageableImpl);

		} else if (null != requestParams.get("status")) {
			page = findByStatus(requestParams.get("status"), pageableImpl);

		} else {
			page = listAllByPage(pageableImpl);
		}

		for (Object[] row : page) {
			studentResponse = new StudentResponse();
			studentResponse.setStudentName(row[0].toString());
			studentResponse.setGender(row[1].toString());
			studentResponse.setStatus(row[2].toString());
			studentResponse.setClassDiv(row[3].toString());
			studentResponse.setRollNo(row[4].toString());
			studentResponses.add(studentResponse);
		}
		if(studentResponses.isEmpty())
		{
			studentDetails.setMessage("No Details Found.Please Try Again!!");
		}
		else
		{
			studentDetails.setMessage("Details Fetched Successfully !!");
			studentDetails.setStudentDetails(studentResponses);
		}
		return studentDetails;
	}

}
