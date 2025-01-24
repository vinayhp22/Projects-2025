package com.skyllx.parkingrental.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import com.skyllx.parkingrental.constant.FileConstant;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	public WebInitConfig() {
		log.info("Created: "+getClass().getSimpleName());
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("running getRootConfigClasses()");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("running getServletConfigClasses()");
		return new Class[] {SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		log.info("running getServletMappings()");
		return new String[] {"/"};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		int maxSize= FileConstant.MAX_FILE_SIZE;	//800000000;
		MultipartConfigElement multipartConfigElement=
									new MultipartConfigElement(FileConstant.TEMP_FILE_LOCATION, maxSize, maxSize*2, maxSize/2);
		registration.setMultipartConfig(multipartConfigElement);
	}
}
