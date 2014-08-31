package org.ictact.webproceedings.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.ictact.webproceedings.util.CustomLocalDateSerializer;
import org.ictact.webproceedings.util.SlugGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "conferences")
public class Conference extends BaseEntity implements Comparable<Conference> {

	@NotEmpty
	private String title;

	@NotEmpty
	private String topic;

	@NotEmpty
	private String editors;

	@NotEmpty
	private String series;

	private String issn;

	private String url;

	@NotNull
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private Date dateFrom;

	@NotNull
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private Date dateTo;

	@NotEmpty
	private String venue;

	private String isbn;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIssn() {
		return issn;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

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

	public String getEditors() {
		return editors;
	}

	public void setEditors(String editors) {
		this.editors = editors;
	}

	@JsonIgnore
	public String getTitleSlug() {
		return SlugGenerator.toSlug(this.title);
	}

	@JsonIgnore
	public String getYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateTo);
		String year = Integer.toString(cal.get(Calendar.YEAR));
		return year;
	}

	@JsonIgnore
	public String getPdf() {
		return String.format("%s-web-proceedings.pdf", getTitleSlug());
	}

	@JsonIgnore
	public String getDate() {
		return new SimpleDateFormat("yyyy/MM/dd/").format(dateFrom);
	}

	@Override
	public int compareTo(Conference conf) {
		return conf.dateTo.compareTo(this.dateTo);
	}

}
