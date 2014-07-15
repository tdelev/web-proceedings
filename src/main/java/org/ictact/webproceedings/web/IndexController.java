package org.ictact.webproceedings.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.PaperAuthor;
import org.ictact.webproceedings.model.PaperType;
import org.ictact.webproceedings.service.AuthorService;
import org.ictact.webproceedings.service.ConferenceService;
import org.ictact.webproceedings.service.PaperAuthorService;
import org.ictact.webproceedings.service.PaperService;
import org.ictact.webproceedings.service.PaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller used to showcase what happens when an exception is thrown
 * 
 */
@Controller
public class IndexController {

	@Autowired
	private ConferenceService confService;

	@Autowired
	private PaperService paperService;

	@Autowired
	private PaperTypeService paperTypeService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PaperAuthorService paperAuthorService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		List<Conference> conferences = new ArrayList<Conference>(
				confService.findAll());
		Collections.sort(conferences);
		Conference latestConf = conferences.get(0);
		conferences.remove(0);
		List<Conference> conf = conferences;
		ModelAndView result = new ModelAndView("index", "conferences", conf);
		result.addObject("latestConf", latestConf);
		return result;
	}

	@RequestMapping(value = "/conferences", method = RequestMethod.GET)
	public String invalid() {
		return "redirect:/";
	}

	@RequestMapping(value = "/conference/{conferenceId}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView conference(@PathVariable Long conferenceId) {
		Conference conf = confService.findById(conferenceId);
		List<Paper> papers = paperService.findByConferenceId(conferenceId);
		ModelAndView result = new ModelAndView("conference");
		List<PaperType> types = new ArrayList<PaperType>(
				paperTypeService.findAll());
		result.addObject("conference", conf);
		result.addObject("papers", papers);
		Map<Long, List<Paper>> papersMap = new HashMap<Long, List<Paper>>();
		for (Paper p : papers) {
			Long key = p.getType().getId();
			List<Paper> list = papersMap.get(key);
			if (list == null) {
				list = new ArrayList<Paper>();
			}
			list.add(p);
			papersMap.put(key, list);
		}
		result.addObject("papersMap", papersMap);
		result.addObject("types", types);
		return result;
	}

	@RequestMapping(value = "/{conferenceTitle:[a-zA-Z0-9-]+}/paper/{paperId:\\d+}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView paper(@PathVariable String conferenceTitle,
			@PathVariable Long paperId) {
		Paper paper = paperService.findById(paperId);
		ModelAndView result = new ModelAndView("paper");
		List<PaperAuthor> paperAuthors = paperAuthorService
				.findByPaperId(paperId);
		List<Conference> conferences = new ArrayList<Conference>(
				confService.findAll());
		Collections.sort(conferences);
		result.addObject("paper", paper);
		result.addObject("authors", paperAuthors);
		result.addObject("conferences", conferences);
		return result;
	}

	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public ModelAndView authors() {
		List<Author> authors = new ArrayList<Author>(authorService.findAll());
		return new ModelAndView("author", "authors", authors);
	}

	@RequestMapping(value = "/author/{authorId}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView papersByAuthor(@PathVariable Long authorId) {
		Author author = authorService.findById(authorId);
		List<PaperAuthor> paperList = paperAuthorService
				.findByAuthorId(authorId);
		List<Paper> papers = new ArrayList<Paper>(paperList.size());
		for (PaperAuthor paper : paperList) {
			papers.add(paper.getPaper());
		}
		ModelAndView result = new ModelAndView("papersByAuthor");
		result.addObject("papers", papers);
		result.addObject("author", author);
		return result;
	}

	@RequestMapping("/paper/download/{id}")
	public String downloadPaperById(@PathVariable("id") Long id,
			HttpServletResponse response) {
		Paper paper = paperService.findById(id);
		writeFileToResponse(paper, response);
		return null;
	}

	private void writeFileToResponse(Paper paper, HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			String contentDisposition = String.format("inline;filename=\"%s\"",
					paper.getPaperFileName());
			response.setHeader("Content-Disposition", contentDisposition);
			response.setContentType(paper.getFileContentType());
			response.setContentLength((int) paper.getPaperFile().length());
			IOUtils.copy(paper.getPaperFile().getBinaryStream(), out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
