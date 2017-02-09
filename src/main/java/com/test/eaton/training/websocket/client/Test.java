package com.test.eaton.training.websocket.client;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.xml.datatype.DatatypeConfigurationException;

public class Test {

	public  void main(String[] args) throws ParseException, DatatypeConfigurationException {
		
		LocalDateTime currentTime=LocalDateTime.now();
		ZoneId canada = ZoneId.of("UTC");
		ZonedDateTime zonedDateTime=ZonedDateTime.of(currentTime, canada);
		
		ZonedDateTime dateTime=ZonedDateTime.now();
		Timestamp  time=Timestamp.from(dateTime.toInstant());
	    System.out.println("Zoned date "+time);
	    //System.out.println("Today is : " + today);
	}

}
