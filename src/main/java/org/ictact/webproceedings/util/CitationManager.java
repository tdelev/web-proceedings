package org.ictact.webproceedings.util;

import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.model.Paper;

public class CitationManager {
	public static String bibtex(Paper paper) {
		StringBuilder authors = new StringBuilder();
		int size = paper.getPaperAuthors().size();
		String firstAuthor = null;
		for (int i = 0; i < size; ++i) {
			Author author = paper.getPaperAuthors().get(i).getAuthor();
			authors.append(String.format("%s, %s", author.getLastName(),
					author.getFirstName()));
			if (i != size - 1) {
				authors.append(" and ");
			}
			if (i == 0) {
				firstAuthor = author.getLastName().toLowerCase();
			}
		}
		StringBuilder sb = new StringBuilder();
		String title = String.format("%s%s%s", firstAuthor, paper
				.getConference().getYear(),
				paper.getTitle().substring(0, paper.getTitle().indexOf(" "))
						.toLowerCase());
		sb.append(String.format("@article{%s,\n", title));
		sb.append(String.format("  title={%s}\n", paper.getTitle()));
		sb.append(String.format("  author={%s}\n", authors.toString()));
		sb.append(String.format("  journal={%s, Web Proceedings ISSN %s}\n",
				paper.getConference().getTitle(), paper.getConference()
						.getIssn()));
		sb.append(String.format("  pages={%s}\n",
				paper.getPages().replace("-", "--")));
		sb.append(String.format("  year={%s}\n", paper.getConference()
				.getYear()));
		sb.append("}");
		return sb.toString();
	}
}
