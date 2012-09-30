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
public class Category {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@NotNull
	private String name_cz;

	@NotNull
	private String name_en;

	@NotNull
	private int sortOrder;

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

	public String getName_cz() {
		return name_cz;
	}

	public void setName_cz( String name ) {
		this.name_cz = name;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en( String name_en ) {
		this.name_en = name_en;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder( int sortOrder ) {
		this.sortOrder = sortOrder;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( getName_cz() ).append( " [" ).append( getName_en() ).append( "]" );
		return sb.toString();
	}
}
