package com.project.david.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.david.service.ReportException;
import com.project.david.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportController {

	private ReportService reportService;
	
	public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}

	@GetMapping("/downloadReportPDF")
	public void generateReportPDF(@RequestParam("fileName") String fileName,HttpServletResponse response){
		try {
			reportService.generateReportPDF(fileName,response);
		}catch(ReportException e) {
			e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/onlineReportPDF")
	public void onlineReportPDF(@RequestParam("fileName")String fileName,HttpServletResponse response) {
		try {
			reportService.onlineReportPDF(fileName, response);
		} catch (ReportException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/downloadReportExcel")
	public void generateReportExcel(@RequestParam("fileName") String fileName,HttpServletResponse response){
		try {
			reportService.generateReportExcel(fileName, response);
		} catch (ReportException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
}
