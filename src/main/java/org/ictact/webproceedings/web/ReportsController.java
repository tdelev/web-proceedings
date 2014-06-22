package org.ictact.webproceedings.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/reports")
public class ReportsController {

	private static final String PDF_EXPORT = "pdf";
	private static final String EXCEL_EXPORT = "xls";
	private static final String DOCX_EXPORT = "docx";
	private static String REPORTS_ROOT = "/reports";

	@Autowired
	private DataSource dataSource;

	@RequestMapping(value = "/employment/{id}")
	@ResponseBody
	public void employment(@PathVariable Long id,
			@RequestParam(required = false) String type,
			HttpServletResponse response) throws Exception {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("employment_id", id);
		params.put("companyName", "НЛБ Тутунска банка АД Скопје");
		params.put("companyStreet", "Мајка Тереза");
		params.put("companyStreetNo", "1");
		params.put("companyCity", "1000, Скопје");
		params.put("companyNumber", "4234556");

		generateReport("test-dogovor.jasper", params, type, response);
	}

	public void generateReport(String reportName,
			HashMap<String, Object> parameters, String exportType,
			HttpServletResponse response) throws Exception {

		String reportPath = String.format("%s/%s", REPORTS_ROOT, reportName);

		Connection connection = dataSource.getConnection();
		// Fill the report using an empty data source
		JasperPrint print = JasperFillManager.fillReport(
				ReportsController.class.getResourceAsStream(reportPath),
				parameters, connection);

		JRExporter exporter = null;

		if (exportType != null && exportType.equals(EXCEL_EXPORT)) {
			// Create a Excel exporter
			exporter = new JRXlsExporter();
			response.setContentType("application/vnd.ms-excel");
		} else if (exportType != null && exportType.equals(DOCX_EXPORT)) {
			// Create a Excel exporter
			exporter = new JRDocxExporter();
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		} else {
			exporter = new JRPdfExporter();
			response.setContentType("application/pdf");
		}
		try {
			OutputStream out = response.getOutputStream();
			// Configure the exporter (set output file name and print object)
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			// Export the PDF file
			exporter.exportReport();

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
