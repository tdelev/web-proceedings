package org.ictact.webproceedings.service;

import org.ictact.webproceedings.model.ConferenceMeta;

public interface ConferenceMetaService extends BaseEntityCrudService<ConferenceMeta> {
	ConferenceMeta findByConferenceId(Long id);
}
