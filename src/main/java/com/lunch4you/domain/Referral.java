package com.lunch4you.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity( name="referral" )
public class Referral {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "sender_id" )
	private Customer sender;

	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "recipient_id" )
	private Customer recipient;

	@Column(name = "time_stamp")
	@Temporal( value = TemporalType.TIMESTAMP )
	private Date timestamp;
	
	@Column(name = "referral_message")
	private String referralMessage;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender( Customer sender ) {
		this.sender = sender;
	}

	public Customer getRecipient() {
		return recipient;
	}

	public void setRecipient(Customer recipient) {
		this.recipient = recipient;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp( Date timestamp ) {
		this.timestamp = timestamp;
	}

	public String getReferralMessage() {
		return referralMessage;
	}

	public void setReferralMessage(String referralMessage) {
		this.referralMessage = referralMessage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( id ).append( ") " );
		sb.append( "Referral, sender: " ).append( sender );
		return sb.toString();
	}

}
