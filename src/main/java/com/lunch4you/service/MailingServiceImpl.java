package com.lunch4you.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;

public class MailingServiceImpl implements MailingService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	private String from;

	private String menuSubject;
	
	private String confirmationSubject;

	private String shopURL;

	@Override
	public void sendMenu( final Customer customer, final LinkedHashMap<Long,CategoryWithArticles> categoriesWithArticles ) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare( MimeMessage mimeMessage ) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper( mimeMessage );
				Map<String, Object> model = new HashMap<String, Object>();
				model.put( "token", customer.getToken() );
				model.put( "shopURL", shopURL );
				model.put( "categoriesWithArticles", categoriesWithArticles.values() );
				
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/menuMailTemplate.vm", model  );

				helper.setFrom( from );
				helper.setTo( customer.getEmail() );
				helper.setSubject( menuSubject );
				helper.setText( bodyText );
			}
		};
		mailSender.send( preparator );
	}

	@Override
	public void sendOrderConfirmation( final Order order ) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare( MimeMessage mimeMessage ) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper( mimeMessage );
				Map<String, Object> model = new HashMap<String, Object>();
				model.put( "order", order );
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/confirmationMailTemplate.vm", model  );

				helper.setFrom( from );
				helper.setTo( order.getOwner().getEmail() );
				helper.setSubject( confirmationSubject );
				helper.setText( bodyText );
			}
		};
		mailSender.send( preparator );
	}
	
	public void setFrom( String from ) {
		this.from = from;
	}

	public void setMenuSubject( String subject ) {
		this.menuSubject = subject;
	}
	
	public void setConfirmationSubject( String subject ) {
		this.confirmationSubject = subject;
	}

	public void setShopURL( String shopURL ) {
		this.shopURL = shopURL;
	}
}
