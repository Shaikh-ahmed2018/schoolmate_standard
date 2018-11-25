package com.cerp.rest.util;

import java.util.Random;

public class GenerateCrendentials {

		// This our Password generating method
		// We have use static here, so that we not to
		// make any object for it
		public static char[] generatePwd(int len)
		{
			System.out.println("Generating password using random() : ");
			//System.out.print("Your new password is : ");

			// A strong password has Cap_chars, Lower_chars,
			// numeric value and symbols. So we are using all of
			// them to generate our password
			String Capital_chars = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
			String Small_chars = "abcdefghijkmnopqrstuvwxyz";
			String numbers = "0123456789";
					String symbols = "!#$*=?)";

			String values = Capital_chars + Small_chars +
							numbers + symbols;

			// Using random method
			Random rndm_method = new Random();

			char[] password = new char[len];

			for (int i = 0; i < len; i++)
			{
				// Use of charAt() method : to get character value
				// Use of nextInt() as it is scanning the value as int
				password[i] =
				values.charAt(rndm_method.nextInt(values.length()));

			}
			return password;
		}
		
		public static String generateUname(String firstname,String lastname, String prodname){
			
			Random rnd = new Random();
		    String result;
		    
		    if(lastname == null || lastname.trim().equalsIgnoreCase("")){
		    	lastname = prodname;
		    }
		    
		    result = Character.toString(firstname.charAt(0)); // First char
		    if (lastname.length() > 5)
		      result += lastname.substring(0, 5);
		    else
		      result += lastname;
		    result += Integer.toString(rnd.nextInt(99));
		    System.out.println(result);
		    return result; 
		}
	
}
