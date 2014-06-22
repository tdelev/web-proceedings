package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.repository.PaperRepository;
import org.ictact.webproceedings.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl extends
		BaseEntityCrudServiceImpl<Paper, PaperRepository> implements PaperService {

	@Autowired
	private PaperRepository repository;

	@Override
	protected PaperRepository getRepository() {
		return repository;
	}

}
