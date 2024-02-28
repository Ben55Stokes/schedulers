package com.hglobal.demo.utility;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	public static final String WISHES_TIME = "0 0 12 * * ?";
	public static final String EMAIL_SUBJECT = "Sending Birthday Cheers Your Way! 🎂🎈";

	public static String content(String name, Integer age) {
		String content = "Happy " + age + "th" + " Birthday! 🎉 " + name
				+ " Wishing You a Day Filled with Joy and Celebration!";
		return content;
	}
	
	public static Integer ageCalculator(LocalDate birthday) {
		Integer birthYear= birthday.getYear();
		Integer currentYear= LocalDate.now().getYear();
		return birthYear-currentYear;
	}
}