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
public class Article {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@NotNull
	private String name;

	private String description;

	@NotNull
	private Integer price;

	private Integer dailyLimit;

	private Boolean isActive = true;
	
	private Integer category;
	
	private String name_cz;
	
	private String name_en;
	
	private String description_cz;
	
	private String description_en;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice( Integer price ) {
		this.price = price;
	}

	public Integer getDailyLimit() {
		return this.dailyLimit;
	}

	public void setDailyLimit( Integer dailyLimit ) {
		this.dailyLimit = dailyLimit;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive( Boolean isActive ) {
		this.isActive = isActive;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getName_cz() {
		return name_cz;
	}

	public void setName_cz(String name_cz) {
		this.name_cz = name_cz;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getDescription_cz() {
		return description_cz;
	}

	public void setDescription_cz(String description_cz) {
		this.description_cz = description_cz;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( getName() ).append( " - " ).append( getDescription() );
		return sb.toString();
	}
}
