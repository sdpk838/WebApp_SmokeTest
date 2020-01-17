package com.qa.webapp.testscripts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	
	public void TestMethod(){
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
	}
}
