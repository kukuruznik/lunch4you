package com.lunch4you.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(value={"/META-INF/spring/applicationContext.xml"})
public class JobsTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private Jobs jobs;

	@Test
	public void testEmailMenu() {
		// instead of catching the exception we should run an SMTP server programatically and test the received message
		try {
			jobs.emailMenu();
		} catch ( MailSendException e ) {
			// if the SMPT server is not running, just print the exception message
			System.out.println( e.getMessage() );
		}
	}

}
