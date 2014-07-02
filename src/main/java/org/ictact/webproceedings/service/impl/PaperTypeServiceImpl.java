package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.PaperType;
import org.ictact.webproceedings.repository.PaperTypeRepository;
import org.ictact.webproceedings.service.PaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class PaperTypeServiceImpl extends
		BaseEntityCrudServiceImpl<PaperType, PaperTypeRepository> implements
		PaperTypeService {

	@Autowired
	private PaperTypeRepository repository;

	@Override
	protected PaperTypeRepository getRepository() {
		return repository;
	}

}
