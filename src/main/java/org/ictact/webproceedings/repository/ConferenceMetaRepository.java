package org.ictact.webproceedings.repository;

import org.ictact.webproceedings.model.ConferenceMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceMetaRepository extends JpaRepository<ConferenceMeta, Long> {
	ConferenceMeta findByConferenceId(Long id);
}
