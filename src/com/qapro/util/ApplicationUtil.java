package com.qapro.util;

import java.util.Date;
import java.util.Random;

public class ApplicationUtil {
	
	public static String generateEmailValidationCode(int length) {
		Random random = new Random();		
		
		StringBuilder emailValidationCode = new StringBuilder();
		
		for (int i = 0; i <= length; i++) {
			
			int chr = (int) 'A' + random.nextInt(26);
			
			emailValidationCode.append((char) chr);
		}
		
		return new Date().getTime() + emailValidationCode.toString();
	}

}
