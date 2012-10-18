package com.lunch4you.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessController {

	@RequestMapping(value = "/backoffice/loggedInUser", method = RequestMethod.GET)
	public @ResponseBody
	String getUser(Principal principal) {

		return principal.getName();
	}
}
