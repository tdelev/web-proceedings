package org.ictact.webproceedings.service;

import java.util.Collection;
import java.util.List;

import org.ictact.webproceedings.model.Paper;

public interface PaperService extends BaseEntityCrudService<Paper> {

	List<Paper> findByConferenceId(Long conferenceId);

	
}
