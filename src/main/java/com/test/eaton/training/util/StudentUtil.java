package com.test.eaton.training.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class StudentUtil {

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Timestamp getStartDate() {

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate date = currentTime.toLocalDate();
		Date startDate;
		Timestamp start = null;

		try {
			startDate = format.parse(date + " 00:00:00");
			String startTime = format.format(startDate);
			start = Timestamp.valueOf(startTime);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return start;
	}

	public static Timestamp getEndDate() {
		Timestamp end = null;
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate date = currentTime.toLocalDate();
		Date endDate;
		try {
			endDate = format.parse(date + " 23:59:59");
			String endTime = format.format(endDate);
			end = Timestamp.valueOf(endTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return end;
	}
}
