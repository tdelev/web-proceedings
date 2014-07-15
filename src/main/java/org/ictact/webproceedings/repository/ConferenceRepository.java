package org.ictact.webproceedings.repository;

import org.ictact.webproceedings.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
	
}
