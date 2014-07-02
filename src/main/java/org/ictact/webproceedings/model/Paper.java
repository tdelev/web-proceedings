package org.ictact.webproceedings.model;

import java.io.File;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity {

	@Column(length = 1000)
	private String paperAbstract;
	
	@ManyToOne
	private Conference conference;
	
	@ManyToOne
	private PaperType type;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String keywords;
	
	public PaperType getType() {
		return type;
	}

	public void setType(PaperType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Blob getPaperFile() {
		return paperFile;
	}

	public void setPaperFile(Blob paperFile) {
		this.paperFile = paperFile;
	}

	@JsonIgnore
	private Blob paperFile;

	public String getPaperAbstract() {
		return paperAbstract;
	}

	public void setPaperAbstract(String paperAbstract) {
		this.paperAbstract = paperAbstract;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

}
