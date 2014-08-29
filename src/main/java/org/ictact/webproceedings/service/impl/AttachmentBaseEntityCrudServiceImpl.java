package org.ictact.webproceedings.service.impl;

import java.util.List;

import org.ictact.webproceedings.model.AAttachment;
import org.ictact.webproceedings.repository.AttachmentJpaRepository;
import org.ictact.webproceedings.service.AttachmentBaseEntityCrudService;

public abstract class AttachmentBaseEntityCrudServiceImpl<T extends AAttachment, R extends AttachmentJpaRepository<T>>
		implements AttachmentBaseEntityCrudService<T> {

	protected abstract R getRepository();

	@Override
	public void save(T entity) {
		getRepository().save(entity);

	}

	@Override
	public List<T> findAll() {
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

	@Override
	public List<T> findByObjectId(Long id) {
		return getRepository().findByObjectId(id);
	}

}
