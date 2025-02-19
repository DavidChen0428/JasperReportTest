package com.project.david.pdf;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JREmptyDataSource;

@SpringBootTest
public class exportTest {
	// 直接輸出PDF文件
	@Test
	public void test() throws IOException, JRException{
		// 1.讀取模板文件
		Resource resource=new ClassPathResource("jasperReport/memberlist.jasper");
		System.out.println("Resource path: " + resource.getFile().getAbsolutePath());

		// 確保文件存在
		if(!resource.exists()) {
			throw new IOException("JasperReport template file not found: "+resource.getFilename());
		}
		
		// 2. 設定參數
		Map<String,Object> parms=new HashMap<String,Object>();
		
		// 3.模板和數據整合
		JasperPrint jasperPrint=JasperFillManager.fillReport(new FileInputStream(resource.getFile()), parms,new JREmptyDataSource());
		
		// 4.導出PDF
		JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream("C:\\Users\\DavidChen\\Desktop\\demo.pdf"));
	}
}
