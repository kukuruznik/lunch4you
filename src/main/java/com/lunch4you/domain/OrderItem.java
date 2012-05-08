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
public class OrderItem {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@NotNull
	private String name;
	
	@NotNull
	private Integer amount;
	
	@NotNull
	private Integer unitPrice;
	
	@NotNull
	private Integer totalPrice;
	
	@NotNull
	private Long articleId;

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

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Integer getCount() {
		return amount;
	}

	public void setCount( Integer count ) {
		this.amount = count;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice( Integer unitPrice ) {
		this.unitPrice = unitPrice;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice( Integer totalPrice ) {
		this.totalPrice = totalPrice;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId( Long articleId ) {
		this.articleId = articleId;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( "Order item: " ).append( getName() );
		sb.append( " * " ).append( getCount() );
		sb.append( " = " ).append( getTotalPrice() ).append( " credit(s)" );
		return sb.toString();
	}
}
