package org.ictact.webproceedings.service;

import java.util.Collection;
import java.util.List;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.PaperAuthor;

public interface PaperAuthorService extends BaseEntityCrudService<PaperAuthor> {

	Collection<PaperAuthor> findByPaperId(Long id);

	List<PaperAuthor> findByAuthorId(Long authorId);
}
