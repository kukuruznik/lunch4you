package com.lunch4you.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Article {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	@Version
	private Integer version;

	@NotNull
	private Integer price;

	private Integer dailyLimit;

	private Boolean isActiveDelivery = true;

	private Boolean isActiveRestaurant = true;

	@Column(name = "new_flag")
	private Boolean isNew = false;

	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "category_id" )
	private Category category;

	private String code;

	@NotNull
	private String name_cz;

	@NotNull
	private String name_en;

	private String description_cz;

	private String description_en;

	private String package_type;

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

	public Boolean getIsActiveDelivery() {
		return isActiveDelivery;
	}

	public void setIsActiveDelivery(Boolean isActiveDelivery) {
		this.isActiveDelivery = isActiveDelivery;
	}

	public Boolean getIsActiveRestaurant() {
		return isActiveRestaurant;
	}

	public void setIsActiveRestaurant(Boolean isActiveRestaurant) {
		this.isActiveRestaurant = isActiveRestaurant;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getPackage_type() {
		return package_type;
	}

	public void setPackage_type( String package_type ) {
		this.package_type = package_type;
	}
	
	public boolean isSoldOut(){
		return dailyLimit != null && dailyLimit == 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( '(' ).append( getId() ).append( ") " );
		sb.append( getName_cz() ).append( " [" ).append( getName_en() ).append( "] - " );
		sb.append( getDescription_cz() ).append( " [" ).append( getDescription_en() ).append( "]" );
		return sb.toString();
	}
}
