package com.lunch4you.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class DeliveryLocation {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String abbreviation;

	private Boolean isActive = true;
	
	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getAbbreviation () {
		return this.abbreviation;
	}

	public void setAbbreviation( String abbreviation ) {
		this.abbreviation = abbreviation;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive( Boolean isActive ) {
		this.isActive = isActive;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( getName() ).append( " - " ).append( getAbbreviation() );
		return sb.toString();
	}
}
