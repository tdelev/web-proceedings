package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.PaperAttachment;
import org.ictact.webproceedings.repository.PaperAttachmentRepository;
import org.ictact.webproceedings.service.PaperAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paper_attachment")
public class PaperAttachmentServiceImpl
		extends
		AttachmentBaseEntityCrudServiceImpl<PaperAttachment, PaperAttachmentRepository>
		implements PaperAttachmentService {

	@Autowired
	private PaperAttachmentRepository repository;

	@Override
	protected PaperAttachmentRepository getRepository() {
		return repository;
	}

	@Override
	public PaperAttachment createNew() {
		return new PaperAttachment();
	}
}
