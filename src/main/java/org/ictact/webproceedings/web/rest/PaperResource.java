package org.ictact.webproceedings.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.service.PaperService;
import org.ictact.webproceedings.util.RequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		return service.findAll();
	}

	@RequestMapping(value = "/paged", method = RequestMethod.GET, produces = "application/json")
	public Page<Paper> getPaged(@RequestParam int page,
			@RequestParam int count, HttpServletRequest request) {
		Sort sort = RequestProcessor.sorting(request);
		Pageable pageable = new PageRequest(page - 1, count, sort);
		Specification<Paper> specification = RequestProcessor
				.getSpecification(request);
		return service.findAll(specification, pageable);
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
