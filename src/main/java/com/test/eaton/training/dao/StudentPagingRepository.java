package com.test.eaton.training.dao;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.test.eaton.training.entity.StudentDetails;



interface StudentPagingRepository extends
		PagingAndSortingRepository<StudentDetails, Integer> {
	@Query("select s.studentName, s.gender,a.status,s.classDiv,s.id from students s join s.attendance a where s.id=a.rollNo and s.studentName=:studentName and a.date >= :start and a.date<= :end")
	Page<Object[]> findByStudentName(@Param("studentName") String studentName,
			@Param("start") Timestamp date, @Param("end") Timestamp end,
			Pageable pageable);

	@Query("select s.studentName, s.gender,a.status,s.classDiv,s.id from students s join s.attendance a where s.id=a.rollNo and s.id=:id  and a.date >= :start and a.date<= :end")
	Page<Object[]> findById(@Param("id") long id,
			@Param("start") Timestamp date, @Param("end") Timestamp end,
			Pageable pageable);

	@Query("select s.studentName, s.gender,a.status,s.classDiv,s.id from students s join s.attendance a where s.id=a.rollNo and s.classDiv=:classDiv and a.date >= :start and a.date<= :end")
	Page<Object[]> findByClassDiv(@Param("classDiv") String classDiv,
			@Param("start") Timestamp date, @Param("end") Timestamp end,
			Pageable pageable);

	@Query("select s.studentName, s.gender,a.status,s.classDiv,s.id from students s join s.attendance a where s.id=a.rollNo and a.status=:status and a.date >= :start and a.date<= :end")
	Page<Object[]> findByStatus(@Param("status") String status,
			@Param("start") Timestamp date, @Param("end") Timestamp end,
			Pageable pageable);
	@Query("select s.studentName, s.gender,a.status,s.classDiv,s.id from students s join s.attendance a where s.id=a.rollNo and a.date >= :start and a.date<= :end")
	public Page<Object[]> listAllByPage(@Param("start") Timestamp date,
			@Param("end") Timestamp end, Pageable pageable);

	/*
	 * @Query(
	 * "SELECT * from userdetails u where u.name =:name or u.rollNo=:rollNo or u.class=:class or u.status=status"
	 * ) Page<StudentDetails> fetchDetails(@Param("name") String name,
	 * 
	 * @Param("rollNo") String rollNo, @Param("class") String division,
	 * 
	 * @Param("status") String status);
	 * 
	 * { id:1, name:'Name', rollNo:'655V67H', class:'6C', status:'present' }
	 * 
	 * @Query(
	 * "SELECT * from students u where u.updated_time=current_date and ( u.student_name =:name or u.id=:rollNo or u.class=:division )"
	 * ) Page<StudentDetails> fetchDetails(@Param("name") String name,
	 * 
	 * @Param("rollNo") String rollNo, @Param("class") String division);
	 */

}