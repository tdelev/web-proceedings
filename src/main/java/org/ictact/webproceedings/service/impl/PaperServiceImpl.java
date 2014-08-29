package org.ictact.webproceedings.service.impl;

import java.util.List;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.repository.PaperRepository;
import org.ictact.webproceedings.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl extends
		BaseEntityCrudServiceImpl<Paper, PaperRepository> implements
		PaperService {

	@Autowired
	private PaperRepository repository;

	@Override
	protected PaperRepository getRepository() {
		return repository;
	}

	@Override
	public List<Paper> findByConferenceId(Long conferenceId) {
		return repository.findByConferenceIdOrderByNumberAsc(conferenceId);
	}

	@Override
	public Page<Paper> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Paper> findAll(Specification<Paper> specification,
			Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

}
