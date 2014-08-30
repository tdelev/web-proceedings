package org.ictact.webproceedings.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.ConferenceMeta;
import org.ictact.webproceedings.service.ConferenceMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/rest/conference_meta")
public class ConferenceMetaResource {

	@Autowired
	private ConferenceMetaService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ConferenceMeta create(@RequestBody @Valid ConferenceMeta entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ConferenceMeta> getAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ConferenceMeta get(@PathVariable Long id,
			HttpServletResponse response) {
		ConferenceMeta meta = service.findById(id);
		if (meta == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return meta;
	}

	@RequestMapping(value = "/by_conference/{id}", method = RequestMethod.GET, produces = "application/json")
	public ConferenceMeta getByConference(@PathVariable Long id,
			HttpServletResponse response) {
		ConferenceMeta meta = service.findByConferenceId(id);
		if (meta == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return meta;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}
}
