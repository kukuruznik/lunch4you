package com.lunch4you.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	private String contactURL;

	@Override
	public void sendMenu( final Customer customer, final LinkedHashMap<Long,CategoryWithArticles> categoriesWithArticles ) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare( MimeMessage mimeMessage ) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper( mimeMessage , "UTF-8");
				Map<String, Object> model = new HashMap<String, Object>();
				model.put( "customer", customer);
				model.put( "shopURL", shopURL );
				model.put( "contactURL", contactURL );
				model.put( "categoriesWithArticles", categoriesWithArticles.values() );
				
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/menuMailTemplate.vm", model  );

				helper.setFrom( from );
				helper.setTo( customer.getEmail() );
				helper.setSubject( menuSubject );
				helper.setText( bodyText, true);
			}
		};
		mailSender.send( preparator );
	}

	@Override
	public void sendOrderConfirmation( final Order order ) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare( MimeMessage mimeMessage ) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper( mimeMessage , "UTF-8");
				Map<String, Object> model = new HashMap<String, Object>();
				model.put( "order", order );
				// need to create new date, cause order does not have date assigned yet as it is assigned by DB
				// TODO Change this once date on new order is assigned by the app
				model.put( "orderDate", new Date());
				model.put( "shopURL", shopURL );
				model.put( "contactURL", contactURL );
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/confirmationMailTemplate.vm", model  );

				helper.setFrom( from );
				helper.setTo( order.getOwner().getEmail() );
				helper.setSubject( confirmationSubject );
				helper.setText( bodyText, false );
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

	public String getContactURL() {
		return contactURL;
	}

	public void setContactURL(String contactURL) {
		this.contactURL = contactURL;
	}
}
