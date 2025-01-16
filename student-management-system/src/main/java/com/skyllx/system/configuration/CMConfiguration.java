package com.skyllx.system.configuration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import lombok.extern.slf4j.Slf4j;

@EnableWebMvc
@Configuration
@ComponentScan("com.skyllx.system")
@Slf4j
public class CMConfiguration {

	public CMConfiguration() {
		log.info("Created " + this.getClass());
	}

	@Bean
	public ViewResolver viewResolver() {
		log.info("viewResolver");
		return new InternalResourceViewResolver("/", ".jsp");
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		log.info("localContainerEntityManagerFactoryBean");
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("com.skyllx.system.entity");
		bean.setDataSource(dataSource());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return bean;
	}

	@Bean
	public DataSource dataSource() {
		log.info("dataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/student_mngt_sys");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public PasswordEncoder encoder() {
		log.info("Registering PasswordEncoder");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ResourceHttpRequestHandler resourceHttpRequestHandler() {
		log.info("Registering resourceHttpRequestHandler");
		ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
		handler.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
		handler.setLocations(Collections.singletonList(new ClassPathResource("/static/")));
		handler.setSupportedMethods(HttpMethod.GET.name());
//		handler.setServletContext(ServletContext);
		return handler;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
