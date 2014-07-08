package org.ictact.webproceedings.repository;

import java.util.Collection;
import java.util.List;

import org.ictact.webproceedings.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {
	
	List<Paper> findByConferenceId(Long id);

	

	
}
