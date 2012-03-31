package com.lunch4you.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Meal {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@NotNull
	private String name;

	private String description;

	private Integer dailyLimit;

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion( Integer version ) {
		this.version = version;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public Integer getDailyLimit() {
		return this.dailyLimit;
	}

	public void setDailyLimit( Integer dailyLimit ) {
		this.dailyLimit = dailyLimit;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( getName() ).append( " - " ).append( getDescription() );
		return sb.toString();
	}
}
