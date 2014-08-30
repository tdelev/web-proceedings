package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.ConferenceMeta;
import org.ictact.webproceedings.repository.ConferenceMetaRepository;
import org.ictact.webproceedings.service.ConferenceMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceMetaServiceImpl extends
		BaseEntityCrudServiceImpl<ConferenceMeta, ConferenceMetaRepository>
		implements ConferenceMetaService {

	@Autowired
	private ConferenceMetaRepository repository;

	@Override
	protected ConferenceMetaRepository getRepository() {
		return repository;
	}

	@Override
	public ConferenceMeta findByConferenceId(Long id) {
		return repository.findByConferenceId(id);
	}

}
