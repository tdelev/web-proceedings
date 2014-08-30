package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.BaseEntity;

public interface BaseEntityCrudService<T extends BaseEntity> {

	public void save(T entity);

	public List<T> findAll();

	public T findById(Long id);

	public void delete(Long id);

}
