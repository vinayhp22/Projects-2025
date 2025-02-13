package com.skyllx.rental.config;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.JpaTransactionManager;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan(basePackages = "com.skyllx.rental")  // Scans @Component, @Service, @Repository classes
@EnableTransactionManagement  // Enables automatic transactions
@EnableScheduling  // Enables scheduled tasks
@EnableWebMvc
@Slf4j
@EnableJpaRepositories(basePackages = "com.skyllx.rental.dao")
public class AppConfig {

    public AppConfig() {
        log.info("Created " + this.getClass());
    }

    /** ✅ View Resolver (If JSP files are stored in /WEB-INF/views/) */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        log.info("Registering ViewResolver");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");  // Adjust if JSP files are in a different location
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /** ✅ JPA EntityManager Factory */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        log.info("Configuring LocalContainerEntityManagerFactoryBean");
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("com.skyllx.rental.model");
        bean.setDataSource(dataSource());
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);

        // ✅ Hibernate Properties
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        jpaProperties.setProperty("hibernate.format_sql", "true");
        jpaProperties.setProperty("hibernate.current_session_context_class", "thread");

        bean.setJpaProperties(jpaProperties);
        return bean;
    }

    /** ✅ Data Source (Using HikariCP for Connection Pooling) */
    @Bean
    public DataSource dataSource() {
        log.info("Configuring HikariDataSource");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/rental_platform");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMaximumPoolSize(10);  // Optimized for performance
        return dataSource;
    }

    /** ✅ Transaction Manager (JPA Based) */
    @Bean
    public PlatformTransactionManager transactionManager() {
        log.info("Configuring JpaTransactionManager");
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    /** ✅ Password Encoder (BCrypt for Secure Passwords) */
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Registering PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    /** ✅ Static Resource Handler */
    @Bean
    public org.springframework.web.servlet.resource.ResourceHttpRequestHandler resourceHttpRequestHandler() {
        log.info("Registering resourceHttpRequestHandler");
        org.springframework.web.servlet.resource.ResourceHttpRequestHandler handler = 
                new org.springframework.web.servlet.resource.ResourceHttpRequestHandler();
        handler.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        handler.setSupportedMethods(HttpMethod.GET.name());
        return handler;
    }

    /** ✅ Multipart Resolver (For File Uploads) */
    @Bean
    public MultipartResolver multipartResolver() {
        log.info("Registering MultipartResolver");
        return new StandardServletMultipartResolver();
    }

    /** ✅ UTF-8 Character Encoding Filter */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        log.info("Registering CharacterEncodingFilter");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
