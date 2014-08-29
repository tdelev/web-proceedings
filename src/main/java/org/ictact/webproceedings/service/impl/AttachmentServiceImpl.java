package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.AAttachment;
import org.ictact.webproceedings.repository.AttachmentRepository;
import org.ictact.webproceedings.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends
		BaseEntityCrudServiceImpl<AAttachment, AttachmentRepository> implements
		AttachmentService {

	@Autowired
	private AttachmentRepository repository;

	@Override
	protected AttachmentRepository getRepository() {
		return repository;
	}

}
