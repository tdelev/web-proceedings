package org.ictact.webproceedings.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paper_authors")
public class PaperAuthor extends BaseEntity {

	@ManyToOne
	private Paper paper;

	@ManyToOne
	private Author author;

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
