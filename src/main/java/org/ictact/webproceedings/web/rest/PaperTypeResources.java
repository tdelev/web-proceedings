package org.ictact.webproceedings.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.PaperType;
import org.ictact.webproceedings.service.PaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/paperTypes")
public class PaperTypeResources {

	@Autowired
	private PaperTypeService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public PaperType create(@RequestBody @Valid PaperType entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<PaperType> getAll() {
		Collection<PaperType> paperTypes = service.findAll();
		return new ArrayList<PaperType>(paperTypes);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public PaperType get(@PathVariable Long id, HttpServletResponse response) {
		PaperType paperTypes = service.findById(id);
		if (paperTypes == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return paperTypes;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

}
