package org.ictact.webproceedings.web.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.ictact.webproceedings.exceptions.AttachmentBlobCreationException;
import org.ictact.webproceedings.model.AAttachment;
import org.ictact.webproceedings.model.PaperAttachment;
import org.ictact.webproceedings.service.AttachmentBaseEntityCrudService;
import org.ictact.webproceedings.service.AttachmentService;
import org.ictact.webproceedings.service.PaperAttachmentService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/data/rest/attachments")
public class AttachmentResource implements ApplicationContextAware {

	private ApplicationContext ctx;

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private PaperAttachmentService paService;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public String deleteAttachment(@PathVariable("id") Long id) {
		attachmentService.delete(id);
		return null;
	}

	@RequestMapping(value = "/{bean}/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Collection<AAttachment> objectAttachments(
			@PathVariable("id") Long id, @PathVariable("bean") String bean,
			HttpServletResponse response) {
		AttachmentBaseEntityCrudService service = ctx.getBean(bean,
				AttachmentBaseEntityCrudService.class);
		Collection<AAttachment> attachments = service.findByObjectId(id);
		attachments = attachments == null ? new ArrayList<AAttachment>()
				: new ArrayList<AAttachment>(attachments);
		return attachments;
	}

	@RequestMapping(value = "/{bean}/{id}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AAttachment addAttachment(MultipartFile file, @PathVariable Long id,
			@PathVariable("bean") String bean)
			throws AttachmentBlobCreationException {
		if (file != null) {
			AttachmentBaseEntityCrudService service = ctx.getBean(bean,
					AttachmentBaseEntityCrudService.class);
			AAttachment attachment = service.createNew();
			try {
				setAttachment(attachment, file);
			} catch (Exception e) {
				throw new AttachmentBlobCreationException(e);
			}
			attachment.setObjectId(id);
			service.save(attachment);
			return attachment;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/paper/{id}", method = RequestMethod.POST, produces = "application/json")
	public PaperAttachment addPaperFile(MultipartFile file,
			@PathVariable Long id) throws AttachmentBlobCreationException,
			IOException {
		if (file != null) {
			List<PaperAttachment> attachments = paService.findByObjectId(id);
			PaperAttachment paperFile = null;
			if (attachments == null || attachments.isEmpty()) {
				paperFile = new PaperAttachment();
			} else {
				paperFile = attachments.get(0);
			}
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				setAttachment(paperFile, file);
			} catch (Exception e) {
				throw new AttachmentBlobCreationException(e);
			}
			paperFile.setObjectId(id);
			paService.save(paperFile);
			return paperFile;
		} else {
			return null;
		}
	}

	private void setAttachment(AAttachment attachment, MultipartFile file)
			throws SerialException, SQLException, IOException {
		attachment.setData(new SerialBlob(file.getBytes()));
		attachment.setFileName(file.getOriginalFilename());
		attachment.setContentType(file.getContentType());
		attachment.setSize(file.getSize());
	}

	private void setAttachment(AAttachment attachment, MultipartFile file,
			ByteArrayOutputStream baos) throws SerialException, SQLException,
			IOException {
		attachment.setData(new SerialBlob(baos.toByteArray()));
		attachment.setFileName(file.getOriginalFilename());
		attachment.setContentType(file.getContentType());
		attachment.setSize((long) baos.size());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;

	}

}
