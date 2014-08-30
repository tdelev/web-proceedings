package org.ictact.webproceedings.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conference_meta")
public class ConferenceMeta extends BaseEntity {

	@OneToOne
	private Conference conference;

	@Lob
	private String preface;

	@Lob
	private String committees;

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

	public String getCommittees() {
		return committees;
	}

	public void setCommittees(String committees) {
		this.committees = committees;
	}
}
