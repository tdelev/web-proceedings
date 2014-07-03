package org.ictact.webproceedings.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/papers")
public class PaperResource {

	@Autowired
	private PaperService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Paper create(@RequestBody @Valid Paper paper) {
		service.save(paper);
		return paper;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Paper> getAll() {
		return new ArrayList<Paper>(service.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Paper get(@PathVariable Long id, HttpServletResponse response) {
		Paper paper = service.findById(id);
		if (paper == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return paper;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

}
