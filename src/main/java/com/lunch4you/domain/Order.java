package com.lunch4you.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity( name="plain_order" )
public class Order {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "owner_id" )
	private Customer owner;

	@NotNull
	@Enumerated( EnumType.STRING )
	private Status status;

	@OneToMany( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } ) // TODO: this should be lazy in the future, but now we have only one item / order
	@JoinColumn( name="order_id", insertable=true, nullable=false )
	private List<OrderItem> items;

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

	public Customer getOwner() {
		return owner;
	}

	public void setOwner( Customer owner ) {
		this.owner = owner;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems( List<OrderItem> items ) {
		this.items = items;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus( Status status ) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( id ).append( ") " );
		sb.append( "Order, owner: " ).append( owner );
		sb.append( ", items: " ).append( items );
		return sb.toString();
	}

	public enum Status {
		OPEN, CLOSED;
	}
}
