package org.ictact.webproceedings.service;

import java.util.Collection;

import org.ictact.webproceedings.model.PaperAuthor;

public interface PaperAuthorService extends BaseEntityCrudService<PaperAuthor> {

	Collection<PaperAuthor> findByPaperId(Long id);


}
