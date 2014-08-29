package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PaperService extends BaseEntityCrudService<Paper> {

	List<Paper> findByConferenceId(Long conferenceId);

	Page<Paper> findAll(Pageable pageable);
	
	Page<Paper> findAll(Specification<Paper> specification, Pageable pageable);
}
