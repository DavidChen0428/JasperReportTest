package com.project.david.pdf;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
public class exportTest {
	// 直接輸出PDF文件
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() throws Exception{
		generateReportExcel();
	}
	
	public void generateReportPDF(){
		try(Connection connection=dataSource.getConnection()){
			// 加載模板文件
			InputStream reportStream=dataSource.getClass().getResourceAsStream("/jasperReport/MemberReportList.jrxml");
			JasperReport jasperReport=JasperCompileManager.compileReport(reportStream);
		
			// 填充參數(根據需要添加參數)
			Map<String,Object> parameters=new HashMap<>();
			
			// 填充報表
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, connection);
			
			// 將報表輸出為PDF文件
			File pdfFile=new File("C:\\Users\\DavidChen\\Desktop\\demo.pdf");
			try (FileOutputStream outputStream=new FileOutputStream(pdfFile)){
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(JRException e) {
			e.printStackTrace();
		}
	}
	
	public void generateReportExcel() {
		try(Connection connection=dataSource.getConnection()){
			// 加載模板文件
			InputStream reportStream=dataSource.getClass().getResourceAsStream("/jasperReport/MemberReportList.jrxml");
			JasperReport jasperReport=JasperCompileManager.compileReport(reportStream);
		
			// 填充參數(根據需要添加參數)
			Map<String,Object> parameters=new HashMap<>();
			
			// 填充報表
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameters, connection);
			
			// 將報表輸出為PDF文件
			File xlsFile=new File("C:\\Users\\DavidChen\\Desktop\\demo.xls");
			JRXlsxExporter exporter=new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsFile));
			
			SimpleXlsxReportConfiguration configuration=new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);
			
			exporter.exportReport();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(JRException e) {
			e.printStackTrace();
		}
	}
}
