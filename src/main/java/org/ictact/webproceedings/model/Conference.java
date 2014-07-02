package org.ictact.webproceedings.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.ictact.webproceedings.util.SlugGenerator;

@Entity
@Table(name = "conferences")
public class Conference extends BaseEntity {

	@NotEmpty
	private String title;

	@NotEmpty
	private String topic;

	@NotEmpty
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
