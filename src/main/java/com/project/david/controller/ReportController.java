package com.project.david.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
@CrossOrigin
public class ReportController {
	
	@GetMapping("/reportToPDF")
	public void generatePDFReport(HttpServletResponse response) throws Exception{
			// 設置回應的內容類型和標頭 
			response.setContentType("application/pdf"); 
			response.setHeader("Content-Disposition", "inline; filename=report.pdf"); 
			// 將報告輸出為 PDF 並寫入回應輸出流 
			JasperExportManager.exportReportToPdfStream(jasperaction(), response.getOutputStream());
	}
	
	@GetMapping("/reportToExcel")
	public void generateExcelReport(HttpServletResponse response) {
		try {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");
		
		JRXlsxExporter xlsExporter=new JRXlsxExporter();
		xlsExporter.setExporterInput(new SimpleExporterInput(jasperaction()));
		xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		SimpleXlsxReportConfiguration configuration=new SimpleXlsxReportConfiguration();
		xlsExporter.setConfiguration(configuration);
		xlsExporter.exportReport();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private JasperPrint jasperaction() throws Exception{
		// 載入jasper檔案
		File file = new File("src/main/resources/Report.Jasper/Blank_A4.jasper"); 
		FileInputStream inputStream = new FileInputStream(file); 
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping", "root", "1234");
		// 設置空參數 
		Map<String, Object> parameters = new HashMap<>(); 
		// 填充報告 
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,conn);
		return jasperPrint;
	}
}
