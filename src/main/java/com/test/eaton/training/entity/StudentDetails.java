package com.test.eaton.training.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity(name = "students")
public class StudentDetails {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "student_name")
	private String studentName;
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "class")
	@JsonProperty("class")
	private String classDiv;
	
	@Column(name="created_time")
	private Timestamp createdTime;
	@Column(name="updated_time")
	private Timestamp updatedTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roll_no", referencedColumnName = "", nullable = false)
    private List<Attendance> attendance;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClassDiv() {
		return classDiv;
	}

	public void setClassDiv(String classDiv) {
		this.classDiv = classDiv;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	

}
