package com.lunch4you.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;


public class JobsImpl implements Jobs {

	@Autowired
	private MenuService menuService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	private String from;

	private String subject;

	@Override
	public void emailMenu() {
		List<Customer> allCustomers = menuService.getAllCustomers();
		List<Article> menu = menuService.getMenu();

		for ( Customer customer : allCustomers ) {
			sendEmail( customer, menu );
		}
	}

	private void sendEmail( final Customer customer, final List<Article> menu ) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare( MimeMessage mimeMessage ) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper( mimeMessage );
				Map<String, Object> model = new HashMap<String, Object>();
				model.put( "token", customer.getToken() );
				model.put( "menu", menu );
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/menuMailTemplate.vm", model  );

				helper.setFrom( from );
				helper.setTo( customer.getEmail() );
				helper.setSubject( subject );
				helper.setText( bodyText );
			}
		};
		mailSender.send( preparator );
	}
	
	public void setFrom( String from ) {
		this.from = from;
	}

	public void setSubject( String subject ) {
		this.subject = subject;
	}
}
