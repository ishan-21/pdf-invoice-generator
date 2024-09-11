package com.ishan.context;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.ishan"})
@PropertySource("classpath:/application.properties")
//@PropertySource("classpath:/someOtherFile.properties")
@EnableWebMvc // THIS ENABLES RESPONSE BODY TO WORK IN THE @RestController
public class ApplicationConfiguration {

//    @Bean // de-comment if using validation though @RequestParam
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

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/views/");
        // classpath is /src/main/resources
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());

        return viewResolver;
    }



}
