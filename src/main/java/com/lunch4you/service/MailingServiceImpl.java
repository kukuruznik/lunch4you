package com.lunch4you.service;

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
import com.lunch4you.domain.Referral;

public class MailingServiceImpl implements MailingService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	private String backofficeEmailAddress;
	
	private String contactDetails;
	
	private String from;

	private String menuSubject;
	
	private String confirmationSubject;

	private String referralSubject;

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
				model.put( "contactDetails", contactDetails );
				model.put( "categoriesWithArticles", categoriesWithArticles.values() );
				
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/menuMailTemplate.vm", "UTF-8", model  );

				helper.setFrom( from );
				helper.setTo( customer.getEmail() );
				helper.setSubject( menuSubject );
				helper.setText( bodyText, true );
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
				model.put( "customer", order.getOwner() );
				model.put( "order", order );
				// need to create new date, cause order does not have date assigned yet as it is assigned by DB
				// TODO Change this once date on new order is assigned by the app
				model.put( "orderDate", new Date());
				model.put( "shopURL", shopURL );
				model.put( "contactURL", contactURL );
				model.put( "contactDetails", contactDetails );
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/confirmationMailTemplate.vm", "UTF-8", model  );

				helper.setFrom( from );
				helper.setTo( order.getOwner().getEmail() );
				helper.setSubject( confirmationSubject );
				helper.setText( bodyText, false );
			}
		};
		mailSender.send( preparator );
	}
	
	@Override
	public void sendReferral(final Referral referral) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare( MimeMessage mimeMessage ) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper( mimeMessage , "UTF-8");
				Map<String, Object> model = new HashMap<String, Object>();
				model.put( "referral", referral );
				String bodyText = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "META-INF/velocity/referralMailTemplate.vm", "UTF-8", model  );

				helper.setFrom( from );
				helper.setTo( referral.getRecipient().getEmail() );
				helper.setBcc( backofficeEmailAddress );
				helper.setSubject( referralSubject );
				helper.setText( bodyText, true );
			}
		};
		mailSender.send( preparator );
		
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getBackofficeEmailAddress() {
		return backofficeEmailAddress;
	}

	public void setBackofficeEmailAddress(String backofficeEmailAddress) {
		this.backofficeEmailAddress = backofficeEmailAddress;
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

	public String getReferralSubject() {
		return referralSubject;
	}

	public void setReferralSubject(String referralSubject) {
		this.referralSubject = referralSubject;
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
