package org.ictact.webproceedings.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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

	@JsonIgnore
	@Basic(fetch = FetchType.LAZY)
	@Lob
	private Blob paperFile;

	private String fileContentType;

	private String paperFileName;

	@NotEmpty
	private String pages;

	private String url;

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getPaperFileName() {
		return paperFileName;
	}

	public void setPaperFileName(String paperFileName) {
		this.paperFileName = paperFileName;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Blob getPaperFile() {
		return paperFile;
	}

	public void setPaperFile(Blob paperFile) {
		this.paperFile = paperFile;
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

	@JsonIgnore
	public String getTitleSlug() {
		return SlugGenerator.toSlug(title);
	}

	@JsonIgnore
	public int getAuthorsSize() {
		return paperAuthors.size();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
