
package org.ictact.webproceedings.repository;

import java.util.Collection;
import java.util.List;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.PaperAuthor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperAuthorRepository extends
		JpaRepository<PaperAuthor, Long> {

	Collection<PaperAuthor> findByPaperId(Long id);

	List<PaperAuthor> findByAuthorId(Long id);
}
