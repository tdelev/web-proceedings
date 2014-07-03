
package org.ictact.webproceedings.repository;

import java.util.Collection;

import org.ictact.webproceedings.model.PaperAuthor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperAuthorRepository extends
		JpaRepository<PaperAuthor, Long> {

	Collection<PaperAuthor> findByPaperId(Long id);
}
