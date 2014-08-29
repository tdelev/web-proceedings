package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.AAttachment;

public interface AttachmentBaseEntityCrudService<T extends AAttachment> extends
		BaseEntityCrudService<T> {

	public List<T> findByObjectId(Long id);

	public T createNew();

}
