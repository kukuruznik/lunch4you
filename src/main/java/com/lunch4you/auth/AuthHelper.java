package com.lunch4you.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.lunch4you.domain.Customer;

public class AuthHelper {
	
	private Map<String, SignInRequest> requests = new HashMap<String, SignInRequest>();
	
	private static String generatePin() {
		int minimum = 1000;
		int maximum = 9999;
		
		Random rn = new Random();
		int n = maximum - minimum + 1;
		int i = rn.nextInt() % n;
		int randomNum =  minimum + i;
		if(randomNum < 0) 
			randomNum = randomNum * -1;
		
		return String.format("%04d", randomNum );
	}

	public SignInRequest createSignInRequest(Customer customer) {
		String email = customer.getEmail();
		String pin = generatePin();
		
		SignInRequest req = new SignInRequest(email, pin);
		
		requests.put(email, req);
		
		return req;
	}
	
	public static class SignInRequest {
		String email;
		String pin;

		public SignInRequest(String email, String pin) {
			super();
			this.email = email;
			this.pin = pin;
		}

		public String getPin() {
			return pin;
		}
		
	}

	public boolean verifyPin(String email, String pin) {
		SignInRequest req = requests.get( email );
		if(req == null)
			return false;
		if(req.email.equals(email) && req.pin.equals(pin) )
			return true;
		return false;
	}

}
