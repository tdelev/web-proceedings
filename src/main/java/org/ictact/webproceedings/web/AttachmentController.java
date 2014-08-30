package org.ictact.webproceedings.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.ictact.webproceedings.model.AAttachment;
import org.ictact.webproceedings.model.ConferenceAttachment;
import org.ictact.webproceedings.model.PaperAttachment;
import org.ictact.webproceedings.service.AttachmentService;
import org.ictact.webproceedings.service.ConferenceAttachmentService;
import org.ictact.webproceedings.service.PaperAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private PaperAttachmentService paService;

	@Autowired
	private ConferenceAttachmentService caService;

	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
	public String download(@PathVariable("id") Long id,
			HttpServletResponse response) {
		AAttachment attachment = attachmentService.findById(id);
		writeFileToResponse(attachment, response);
		return null;
	}

	@RequestMapping(value = "/attachment/paper/{id}", method = RequestMethod.GET)
	public String downloadPaper(@PathVariable("id") Long id,
			HttpServletResponse response) {
		List<PaperAttachment> pa = paService.findByObjectId(id);
		if (pa != null && pa.size() > 0) {
			PaperAttachment file = pa.get(0);
			if (file != null) {
				writeFileToResponse(file, response);
			}
		}
		return null;
	}

	@RequestMapping(value = "/attachment/conference/{id}", method = RequestMethod.GET)
	public String downloadProceedings(@PathVariable("id") Long id,
			HttpServletResponse response) {
		List<ConferenceAttachment> ca = caService.findByObjectId(id);
		if (ca != null && ca.size() > 0) {
			ConferenceAttachment file = ca.get(0);
			if (file != null) {
				writeFileToResponse(file, response);
			}
		}
		return null;
	}

	private void writeFileToResponse(AAttachment attachment,
			HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			String contentDisposition = String.format("inline;filename=\"%s\"",
					attachment.getFileName());
			response.setHeader("Content-Disposition", contentDisposition);
			response.setContentType(attachment.getContentType());
			response.setContentLength((int) attachment.getData().length());
			IOUtils.copy(attachment.getData().getBinaryStream(), out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
