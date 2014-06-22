package org.ictact.webproceedings.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/authors")
public class AuthorResource {

	@Autowired
	private AuthorService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Author create(@RequestBody @Valid Author entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAll() {
		Collection<Author> authors = service.findAll();
		return new ArrayList<Author>(authors);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Author get(@PathVariable Long id, HttpServletResponse response) {
		Author author = service.findById(id);
		if (author == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return author;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

}
