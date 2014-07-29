package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.PaperType;

public interface PaperTypeService extends BaseEntityCrudService<PaperType> {
	public List<PaperType> findAllByWeight();
}
