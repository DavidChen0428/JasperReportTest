package com.project.david.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.project.david.service.ReportException;
import com.project.david.service.ReportService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportServiceImpl implements ReportService {

	private DataSource dataSource;
	
	public ReportServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void generateReportPDF(String fileName,HttpServletResponse response) throws ReportException {
		JasperPrint jasperPrint = createJasperPrint(fileName);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");

		try (ServletOutputStream outputStream= response.getOutputStream()){
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			throw new ReportException("資料庫操作錯誤", e);
		} catch (JRException e) {
			throw new ReportException("報表系統錯誤", e);
		}
	}

	@Override
	public void onlineReportPDF(String fileName, HttpServletResponse response) throws ReportException{
		JasperPrint jasperPrint = createJasperPrint(fileName);
		response.setContentType("application/pdf");
		
		try (ServletOutputStream outputStream=response.getOutputStream()){
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (IOException e) {
			throw new ReportException("資料庫操作錯誤", e);
		} catch (JRException e) {
			throw new ReportException("報表系統錯誤", e);
		}
	}
	
	@Override
	public void generateReportExcel(String fileName,HttpServletResponse response) throws ReportException {
		try {
			JasperPrint jasperPrint = createJasperPrint(fileName);

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=\"report.xlsx\"");

			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);

			exporter.exportReport();
		} catch (IOException e) {
			throw new ReportException("輸出串流錯誤", e);
		} catch (JRException e) {
			throw new ReportException("導出配置錯誤", e);
		}

	}

	private JasperPrint createJasperPrint(String fileName) throws ReportException {
		String reportPath="/jasperReport/"+fileName+".jrxml";
		try (Connection connection = dataSource.getConnection()) {
			// 加載模板文件
			InputStream reportStream = dataSource.getClass().getResourceAsStream(reportPath);
			JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

			// 填充參數(根據需要添加參數)
			Map<String, Object> parameters = new HashMap<>();

			// 填充報表
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			return jasperPrint;
		} catch (SQLException e) {
			throw new ReportException("資料庫操作失敗", e);
		} catch (JRException e) {
			throw new ReportException("報表發生問題", e);
		}
	}

	

}
