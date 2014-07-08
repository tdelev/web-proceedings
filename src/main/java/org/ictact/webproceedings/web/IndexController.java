package org.ictact.webproceedings.web;

import java.util.ArrayList;
import java.util.List;

import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.PaperAuthor;
import org.ictact.webproceedings.service.AuthorService;
import org.ictact.webproceedings.service.ConferenceService;
import org.ictact.webproceedings.service.PaperAuthorService;
import org.ictact.webproceedings.service.PaperService;
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
	private AuthorService authorService;

	@Autowired
	private PaperAuthorService paperAuthorService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		List<Conference> conferences = new ArrayList<Conference>(
				confService.findAll());
		return new ModelAndView("index", "conferences", conferences);
	}
	
	@RequestMapping(value = "/conferences", method = RequestMethod.GET)
		public String invalid(){
		return "redirect:/";
		}

	@RequestMapping(value = "/conference/{conferenceId}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView conference(@PathVariable Long conferenceId) {
		Conference conf = confService.findById(conferenceId);
		List<Paper> papers = paperService.findByConferenceId(conferenceId);
		ModelAndView result = new ModelAndView("conference");
		result.addObject("conference", conf);
		result.addObject("papers", papers);
		return result;
	}

	@RequestMapping(value = "/{conferenceTitle:[a-zA-Z0-9-]+}/paper/{paperId:\\d+}/{\\S+}", method = RequestMethod.GET)
	public ModelAndView paper(@PathVariable String conferenceTitle,
			@PathVariable Long paperId) {
		Paper paper = paperService.findById(paperId);
		ModelAndView result = new ModelAndView("paper");
		result.addObject("paper", paper);
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
}
