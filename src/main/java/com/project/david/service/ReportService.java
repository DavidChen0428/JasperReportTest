package com.project.david.service;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	
	// 下載 PDF 檔( jasperreport 轉 PDF )
	void generateReportPDF(String fileName,HttpServletResponse response) throws ReportException;
	// 線上檢閱 PDF 檔( jasperreport 轉 PDF )
	void onlineReportPDF(String fileName,HttpServletResponse response) throws ReportException;
	// 下載 Excel 檔( jasperreport 轉 excel )
	void generateReportExcel(String fileName,HttpServletResponse response) throws ReportException;
}
