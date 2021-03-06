package org.ictact.webproceedings.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.ictact.webproceedings.util.SlugGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity {

	private int number;

	@Column(length = 1000)
	private String paperAbstract;

	@ManyToOne
	private Conference conference;

	@ManyToOne
	private PaperType type;

	@JsonIgnore
	@OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
	private List<PaperAuthor> paperAuthors;

	public List<PaperAuthor> getPaperAuthors() {
		return paperAuthors;
	}

	public void setPaperAuthors(List<PaperAuthor> paperAuthors) {
		this.paperAuthors = paperAuthors;
	}

	@NotEmpty
	private String title;

	@NotEmpty
	private String keywords;

	private int pageFrom;

	private int pageTo;

	private String doi;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(int pageFrom) {
		this.pageFrom = pageFrom;
	}

	public int getPageTo() {
		return pageTo;
	}

	public void setPageTo(int pageTo) {
		this.pageTo = pageTo;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@JsonIgnore
	public String getTitleSlug() {
		return SlugGenerator.toSlug(title);
	}

	@JsonIgnore
	public String getPdf() {
		return String.format("%s.pdf", getTitleSlug());
	}

	@JsonIgnore
	public int getAuthorsSize() {
		return paperAuthors.size();
	}

	@JsonIgnore
	public String getPages() {
		return String.format("%d-%d", pageFrom, pageTo);
	}

}
