package com.ishan.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.ishan"})
@PropertySource("classpath:/application.properties")
//@PropertySource("classpath:/someOtherFile.properties")
@EnableWebMvc // THIS ENABLES RESPONSE BODY TO WORK IN THE @RestController
@EnableTransactionManagement
public class ApplicationConfiguration {

    private static final String DATABASE_NAME = "my_fancy_pdf_invoices_database";
    @Bean
    public DriverManagerDataSource makeBeanOfDataSource(){
        String url = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
        String username = "root";
        String password = "";
        org.springframework.jdbc.datasource.DriverManagerDataSource
                driverManagerDataSource;
        driverManagerDataSource = new DriverManagerDataSource(url, username, password);
        // driverManager.setDriverClassName("com.mysql.cj.jdbc.Driver"); => not necessary to set
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate makeBeanOfJdbcTemplate(@Autowired  DriverManagerDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TransactionManager makeBeanOfTransactionManager(@Autowired  DriverManagerDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SpringResourceTemplateResolver makeBeanOfSpringResourceTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/views/");
        // classpath is /src/main/resources
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine makeBeanOfSpringTemplateEngine(@Autowired SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver makeBeanOfThymeleafViewResolver(@Autowired SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(springTemplateEngine);

        return viewResolver;
    }

        // de-comment if using validation though @RequestParam
        //    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }

    /* No need of the following when we are using Component for the following */
//    @Bean
//    @Scope("singleton")
//    public UserService getUserService(){
//        return new UserService();
//    }
//
//    @Bean
//    @Scope("singleton")
//    public InvoiceService getInvoiceService(UserService userService){
//        return new InvoiceService(userService);
//    }



}
