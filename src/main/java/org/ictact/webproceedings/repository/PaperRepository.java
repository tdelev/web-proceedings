package org.ictact.webproceedings.repository;

import java.util.List;

import org.ictact.webproceedings.model.Paper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaperRepository extends JpaRepository<Paper, Long>, JpaSpecificationExecutor<Paper> {

	List<Paper> findByConferenceIdOrderByNumberAsc(Long id);
	
	List<Paper> findByConferenceId(Specification<Paper> spec, Long id);

}
