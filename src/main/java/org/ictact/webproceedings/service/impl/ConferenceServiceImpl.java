package org.ictact.webproceedings.service.impl;

import java.util.List;

import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.repository.ConferenceRepository;
import org.ictact.webproceedings.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl extends
		BaseEntityCrudServiceImpl<Conference, ConferenceRepository> implements
		ConferenceService {

	@Autowired
	private ConferenceRepository repository;

	@Override
	protected ConferenceRepository getRepository() {
		return repository;
	}

	@Override
	public List<Conference> findAllByOrderByDateFromDesc() {
		return repository.findAllByOrderByDateFromDesc();
	}

}
