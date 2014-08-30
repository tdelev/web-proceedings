package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.ConferenceAttachment;
import org.ictact.webproceedings.repository.ConferenceAttachmentRepository;
import org.ictact.webproceedings.service.ConferenceAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("conference_attachment")
public class ConferenceAttachmentServiceImpl
		extends
		AttachmentBaseEntityCrudServiceImpl<ConferenceAttachment, ConferenceAttachmentRepository>
		implements ConferenceAttachmentService {

	@Autowired
	private ConferenceAttachmentRepository repository;

	@Override
	protected ConferenceAttachmentRepository getRepository() {
		return repository;
	}

	@Override
	public ConferenceAttachment createNew() {
		return new ConferenceAttachment();
	}
}
