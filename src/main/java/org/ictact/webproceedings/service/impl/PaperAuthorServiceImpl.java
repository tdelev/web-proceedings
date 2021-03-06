package org.ictact.webproceedings.service.impl;

import java.util.Collection;
import java.util.List;

import org.ictact.webproceedings.model.PaperAuthor;
import org.ictact.webproceedings.repository.PaperAuthorRepository;
import org.ictact.webproceedings.service.PaperAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperAuthorServiceImpl extends
		BaseEntityCrudServiceImpl<PaperAuthor, PaperAuthorRepository> implements PaperAuthorService {


	@Autowired
	private PaperAuthorRepository repository;

	@Override
	protected PaperAuthorRepository getRepository() {
		return repository;
	}

	@Override
	public List<PaperAuthor> findByPaperId(Long id) {
		return repository.findByPaperId(id);
	}

	@Override
	public List<PaperAuthor> findByAuthorId(Long id) {
		return repository.findByAuthorId(id);
	}

}
