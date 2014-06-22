package org.ictact.webproceedings.service.impl;

import java.util.Collection;

import org.ictact.webproceedings.model.BaseEntity;
import org.ictact.webproceedings.service.BaseEntityCrudService;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseEntityCrudServiceImpl<T extends BaseEntity, R extends JpaRepository<T, Long>>
		implements BaseEntityCrudService<T> {

	protected abstract R getRepository();

	@Override
	public void save(T entity) {
		getRepository().save(entity);

	}

	@Override
	public Collection<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public T findById(Long id) {
		return getRepository().findOne(id);
	}

	@Override
	public void delete(Long id) {
		getRepository().delete(id);
	}

}
