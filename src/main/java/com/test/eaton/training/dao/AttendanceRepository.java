package com.test.eaton.training.dao;

import java.io.Serializable;
import java.sql.Timestamp;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.test.eaton.training.entity.Attendance;



public interface AttendanceRepository extends Repository<Attendance, Serializable>{

	@Query("SELECT count(*) from attendance p where LOWER(p.status) =LOWER(:status) and p.date >= :start and p.date<= :end")
	Long getPresentCount(@Param("status") String status, @Param("start") Timestamp date,@Param("end") Timestamp end);
	
	
	@Modifying(clearAutomatically=true)
	@Query("update attendance t set t.status = :status where t.rollNo = :rollNo and t.date >= :start and t.date<= :end")
	public int setStatus(@Param("status") String status, @Param("rollNo") int rollNo, @Param("start") Timestamp date,@Param("end") Timestamp end);
	
	

}
