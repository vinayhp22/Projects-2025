package com.skyllx.rental.config;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration.Dynamic;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

    private int maxUploadSizwInMb = 3 * 1024 * 1024; // 3MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        log.info("getRootConfigClasses");
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        log.info("getServletConfigClasses");
        return new Class[] { AppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        log.info("getServletMappings");
        return new String[] { "/" };
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.info("configureDefaultServletHandling");
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("addResourceHandlers");
        registry.addResourceHandler("/resources/**").addResourceLocations("/public", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        log.info("customizeRegistration(Dynamic registration)");
        String temDir = "";
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getPath(),
                maxUploadSizwInMb, maxUploadSizwInMb * 2, maxUploadSizwInMb / 2);
        registration.setMultipartConfig(multipartConfigElement);
    }

}