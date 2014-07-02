package org.ictact.webproceedings.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import org.ictact.webproceedings.util.CustomLocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.ictact.webproceedings.util.SlugGenerator;


@Entity
@Table(name = "conferences")
public class Conference extends BaseEntity {

	@NotEmpty
	private String title;

	@NotEmpty
	private String topic;


	@NotNull
	@JsonSerialize(using=CustomLocalDateSerializer.class)
	private Date dateFrom;

	@NotNull
	@JsonSerialize(using=CustomLocalDateSerializer.class)
	private Date dateTo;


	@NotEmpty
	private String venue;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}


	public String getTitleSlug() {
		return SlugGenerator.toSlug(this.title);
	}

}
