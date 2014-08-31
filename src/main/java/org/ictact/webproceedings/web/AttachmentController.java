package org.ictact.webproceedings.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.ictact.webproceedings.model.AAttachment;
import org.ictact.webproceedings.model.Conference;
import org.ictact.webproceedings.model.ConferenceAttachment;
import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.PaperAttachment;
import org.ictact.webproceedings.service.AttachmentService;
import org.ictact.webproceedings.service.ConferenceAttachmentService;
import org.ictact.webproceedings.service.ConferenceService;
import org.ictact.webproceedings.service.PaperAttachmentService;
import org.ictact.webproceedings.service.PaperService;
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

	@Autowired
	private PaperService paperService;

	@Autowired
	private ConferenceService conferenceService;

	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
	public String download(@PathVariable("id") Long id,
			HttpServletResponse response) {
		AAttachment attachment = attachmentService.findById(id);
		writeFileToResponse(attachment, response, "attach");

		return null;
	}

	@RequestMapping(value = "/attachment/paper/{id}/{\\S+}", method = RequestMethod.GET)
	public String downloadPaper(@PathVariable("id") Long id,
			HttpServletResponse response) {
		Paper paper = paperService.findById(id);
		if (paper != null) {
			List<PaperAttachment> pa = paService.findByObjectId(id);
			if (pa != null && pa.size() > 0) {
				PaperAttachment file = pa.get(0);
				if (file != null) {
					writeFileToResponse(file, response, paper.getPdf());
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/attachment/conference/{id}/{\\S+}", method = RequestMethod.GET)
	public String downloadProceedings(@PathVariable("id") Long id,
			HttpServletResponse response) {
		Conference conference = conferenceService.findById(id);
		if (conference != null) {
			List<ConferenceAttachment> ca = caService.findByObjectId(id);
			if (ca != null && ca.size() > 0) {
				ConferenceAttachment file = ca.get(0);
				if (file != null) {
					writeFileToResponse(file, response, conference.getPdf());
				}
			}
		}
		return null;
	}

	private void writeFileToResponse(AAttachment attachment,
			HttpServletResponse response, String fileName) {
		try {
			OutputStream out = response.getOutputStream();
			String contentDisposition = String.format("inline;filename=\"%s\"",
					fileName);
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
