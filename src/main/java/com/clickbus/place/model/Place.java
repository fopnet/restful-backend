package com.clickbus.place.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "places")
@EntityListeners(AuditingEntityListener.class)
public class Place {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Slug is required")
	private String slug;
	
	@NotBlank(message="City is required")
	private String city;
	
	@NotBlank(message="State is required")
	private String state;

	@CreatedDate
	@Column( updatable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime updateAt;
	
	public Place(Long id) {
		this();
		this.id = id;
	}
	
	public Place() {
	}

	/********************************************************************************
	* Sobreescrita do Equals e HashCode e toString
	********************************************************************************/
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	} 

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Place == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Place otherObject = (Place) obj;

		return new EqualsBuilder().append(this.id, otherObject.id).isEquals();
	}
	
	public String toString() {
		return this.id + " - " + this.name; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime dt) {
		this.createdAt = dt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

}
