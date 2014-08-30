package org.ictact.webproceedings.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/conferences")
public class ConferenceResource {

	@Autowired
	private ConferenceService service;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Conference create(@RequestBody @Valid Conference entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Conference> getAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Conference get(@PathVariable Long id, HttpServletResponse response) {
		Conference conference = service.findById(id);
		if (conference == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return conference;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}
}
