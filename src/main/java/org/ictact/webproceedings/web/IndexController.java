package org.ictact.webproceedings.web;

import java.util.ArrayList;
import java.util.List;

import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.service.ConferenceService;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		List<Conference> conferences = new ArrayList<Conference>(
				confService.findAll());
		return new ModelAndView("index", "conferences", conferences);
	}

	@RequestMapping(value = "/conference/{conferenceId}/", method = RequestMethod.GET)
	public ModelAndView conference(@PathVariable Long conferenceId) {
		Conference conf = confService.findById(conferenceId);
		List<Paper> papers = new ArrayList<Paper>(paperService.findAll());
		ModelAndView result = new ModelAndView("conference");
		result.addObject("conference", conf);
		result.addObject("papers", papers);
		return result;
	}
	
	@RequestMapping(value = "/{conferenceTitle:[a-zA-Z0-9-]+}/{paperId}/", method = RequestMethod.GET)
	public ModelAndView paper(@PathVariable String conferenceTitle, @PathVariable Long paperId) {
		ModelAndView result = new ModelAndView("paper");
		return result;
	}

}
