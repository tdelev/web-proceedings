package org.ictact.webproceedings.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.PaperAuthor;
import org.ictact.webproceedings.service.PaperAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/paperAuthors")
public class PaperAuthorResource {

	@Autowired
	private PaperAuthorService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public PaperAuthor create(@RequestBody @Valid PaperAuthor entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<PaperAuthor> getAll() {
		Collection<PaperAuthor> paperAuthors = service.findAll();
		return new ArrayList<PaperAuthor>(paperAuthors);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public PaperAuthor get(@PathVariable Long id, HttpServletResponse response) {
		PaperAuthor paperAuthor = service.findById(id);
		if (paperAuthor == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return paperAuthor;
	}
	
	@RequestMapping(value = "/by_paper/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<PaperAuthor> getByPaper(@PathVariable Long id, HttpServletResponse response) {
		Collection <PaperAuthor> result = service.findByPaperId(id);
		
		return new ArrayList<PaperAuthor> (result);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

}
