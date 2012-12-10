package com.lunch4you.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Customer {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String token;
	
	@NotNull
	private Integer credit;

	private Boolean isActive = true;

	private Boolean isSubscribedMenuWeekly = true;

	private Boolean isSubscribedNews = true;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "default_delivery_location_id" )
	private DeliveryLocation defaultDeliveryLocation;

	public Long getId() {
		return id;
	}
	
	public void setId( Long id ) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion( Integer version ) {
		this.version = version;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken( String token ) {
		this.token = token;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit( Integer credit ) {
		this.credit = credit;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public DeliveryLocation getDefaultDeliveryLocation() {
		return defaultDeliveryLocation;
	}

	public void setDefaultDeliveryLocation(DeliveryLocation deliveryLocation) {
		this.defaultDeliveryLocation = deliveryLocation;
	}

	public Boolean getIsSubscribedMenuWeekly() {
		return isSubscribedMenuWeekly;
	}

	public void setIsSubscribedMenuWeekly(Boolean isSubscribedMenuWeekly) {
		this.isSubscribedMenuWeekly = isSubscribedMenuWeekly;
	}

	public Boolean getIsSubscribedNews() {
		return isSubscribedNews;
	}

	public void setIsSubscribedNews(Boolean isSubscribedNews) {
		this.isSubscribedNews = isSubscribedNews;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( getFirstName() ).append( ' ' ).append( getLastName() );
		sb.append( ", <" ).append( getEmail() ).append( ">, credit: " ).append( getCredit() );
		return sb.toString();
	}
}
