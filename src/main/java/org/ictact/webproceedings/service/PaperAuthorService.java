package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.PaperAuthor;

public interface PaperAuthorService extends BaseEntityCrudService<PaperAuthor> {

	List<PaperAuthor> findByPaperId(Long id);
	
	List<PaperAuthor> findByAuthorId(Long authorId);
}
