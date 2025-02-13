package com.skyllx.expense.config;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan(basePackages = "com.skyllx.expense")  // Scans all @Component, @Service, @Repository classes
@EnableTransactionManagement  // Enables automatic transactions
@EnableScheduling  // Enables scheduled tasks (@Scheduled)
@EnableAspectJAutoProxy  // Enables AOP if used in the project
@EnableWebMvc
@Slf4j
public class AppConfig {

    public AppConfig() {
        log.info("Created" + this.getClass());
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
        bean.setPackagesToScan("com.skyllx.expense.model");
        bean.setDataSource(dataSource());
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
        // Explicitly set Hibernate as the JPA provider
        bean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);

        // Add Hibernate properties
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.current_session_context_class", "thread"); // Fixes the error
        bean.setJpaProperties(jpaProperties);
        return bean;
    }

    @Bean
    public DataSource dataSource() {
        log.info("dataSource");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/expense_tracker");
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

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
