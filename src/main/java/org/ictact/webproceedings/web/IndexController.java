package org.ictact.webproceedings.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.model.ConferenceMeta;
import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.PaperAttachment;
import org.ictact.webproceedings.model.PaperAuthor;
import org.ictact.webproceedings.model.PaperType;
import org.ictact.webproceedings.service.AuthorService;
import org.ictact.webproceedings.service.ConferenceMetaService;
import org.ictact.webproceedings.service.ConferenceService;
import org.ictact.webproceedings.service.PaperAttachmentService;
import org.ictact.webproceedings.service.PaperAuthorService;
import org.ictact.webproceedings.service.PaperService;
import org.ictact.webproceedings.service.PaperTypeService;
import org.ictact.webproceedings.util.CitationManager;
import org.ictact.webproceedings.util.ResourceNotFoundException;
import org.markdownj.MarkdownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

	@Autowired
	private PaperAttachmentService paperAttachmentService;

	@Autowired
	private ConferenceMetaService cmService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		List<Conference> conferences = confService
				.findAllByOrderByDateFromDesc();
		if (conferences.size() == 0) {
			return new ModelAndView("empty");
		}
		Conference latestConf = conferences.get(0);
		conferences.remove(0);
		ModelAndView result = new ModelAndView("index", "conferences",
				conferences);
		result.addObject("latestConf", latestConf);
		return result;
	}

	@RequestMapping(value = "/conferences", method = RequestMethod.GET)
	public String invalid() {
		return "redirect:/";
	}

	@RequestMapping(value = "/conferences/{year:\\d{4}}/{conferenceId:\\d+}", method = RequestMethod.GET)
	public ModelAndView conference(@PathVariable Long conferenceId)
			throws IOException {
		Conference conf = confService.findById(conferenceId);
		ModelAndView result = new ModelAndView("conference");
		result.addObject("conference", conf);
		ConferenceMeta meta = cmService.findByConferenceId(conferenceId);
		if(meta != null) {
			MarkdownProcessor mp = new MarkdownProcessor();
			String preface = mp.markdown(meta.getPreface());
			String committees = mp.markdown(meta.getCommittees());
			result.addObject("preface", preface);
			result.addObject("committees", committees);
		}
		return result;
	}

	@RequestMapping(value = "/conferences/{year:\\d{4}}/{conferenceId:\\d+}/papers", method = RequestMethod.GET)
	public ModelAndView papers(@PathVariable Long conferenceId) {
		Conference conf = confService.findById(conferenceId);
		List<Paper> papers = paperService.findByConferenceId(conferenceId);
		ModelAndView result = new ModelAndView("papers");
		List<PaperType> types = paperTypeService.findAllByWeight();
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

	@RequestMapping(value = "/{year:\\d{4}}/paper/{paperId:\\d+}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView paper(@PathVariable String year,
			@PathVariable Long paperId) {
		Paper paper = paperService.findById(paperId);
		ModelAndView result = new ModelAndView("paper");
		List<PaperAuthor> paperAuthors = paperAuthorService
				.findByPaperId(paperId);
		List<Conference> conferences = confService
				.findAllByOrderByDateFromDesc();
		List<PaperAttachment> attachments = paperAttachmentService
				.findByObjectId(paperId);
		boolean hasAttachments = (attachments != null && attachments.size() > 0);
		result.addObject("paper", paper);
		result.addObject("authors", paperAuthors);
		result.addObject("conferences", conferences);
		result.addObject("hasAttachments", hasAttachments);
		return result;
	}

	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public ModelAndView authors() {
		List<Author> authors = authorService.findAll(new Sort(Direction.ASC,
				"lastName"));
		Map<Character, List<Author>> index = new TreeMap<Character, List<Author>>();
		List<Character> letters = new ArrayList<Character>();
		for (char x = 'A'; x <= 'Z'; ++x) {
			index.put(x, new ArrayList<Author>());
			letters.add(x);
		}
		for (Author author : authors) {
			char key = Character.toUpperCase(author.getLastName().charAt(0));
			List<Author> list = index.get(key);
			if (list != null) {
				list.add(author);
			} else {
				list = new ArrayList<Author>();
				list.add(author);
				index.put(key, list);
				letters.add(key);
			}
		}
		Collections.sort(letters);
		ModelAndView result = new ModelAndView("authors", "index", index);
		result.addObject("letters", letters);
		result.addObject("total", authors.size());
		return result;
	}

	@RequestMapping(value = "/authors/{authorId}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView authorPapers(@PathVariable Long authorId) {
		Author author = authorService.findById(authorId);
		List<PaperAuthor> paperList = paperAuthorService
				.findByAuthorId(authorId);
		List<Paper> papers = new ArrayList<Paper>(paperList.size());
		for (PaperAuthor paper : paperList) {
			papers.add(paper.getPaper());
		}
		ModelAndView result = new ModelAndView("authorPapers");
		result.addObject("papers", papers);
		result.addObject("author", author);
		result.addObject("total", papers.size());
		return result;
	}

	@RequestMapping("/paper/{id}/citation/bibtex")
	public void bibTexCitation(@PathVariable("id") Long id,
			HttpServletResponse response) {
		Paper paper = paperService.findById(id);
		writeTextToResponse(CitationManager.bibtex(paper), response);
	}

	private void writeTextToResponse(String text, HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			response.setContentType("text/plain");
			IOUtils.copy(new ByteArrayInputStream(text.getBytes()), out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/notfound")
	public String notfound() {
		return "notfound";
	}

	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException() {
		return "notfound";
	}
}
