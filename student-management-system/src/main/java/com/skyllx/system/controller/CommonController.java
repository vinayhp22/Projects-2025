package com.skyllx.system.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/")
@Controller
@Slf4j
public class CommonController {
	
	@GetMapping("/download")
	public void onDownload(HttpServletResponse response, @RequestParam String fileName) throws IOException {
		log.info("onDownload in CommonController");
		
		response.setContentType("image/jpeg");
		
		File file = new File("C:\\Users\\USER\\git\\Project-CommonModules\\xworkz-vinayhp-cm\\src\\main\\webapp\\resources\\static\\images\\"+fileName);
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		ServletOutputStream out = response.getOutputStream();
		
		org.apache.commons.io.IOUtils.copy(in, out);
		response.flushBuffer();
	}
}
