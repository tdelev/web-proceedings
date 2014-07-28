package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.Conference;

public interface ConferenceService extends BaseEntityCrudService<Conference> {
	List<Conference> findAllByOrderByDateFromDesc();
}
