package com.service.book.service.enity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publishing_house")
public class PublishingHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long publishingId;

	@Column(name = "name")
	private String name;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;

	public Long getPublishingId() {
		return publishingId;
	}

	public void setPublishingId(Long publishingId) {
		this.publishingId = publishingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
