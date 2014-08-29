package org.ictact.webproceedings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentJpaRepository<T> extends JpaRepository<T, Long> {
	public List<T> findByObjectId(Long id);
}
