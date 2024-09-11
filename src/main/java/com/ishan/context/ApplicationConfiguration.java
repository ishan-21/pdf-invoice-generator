package com.ishan.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.ishan"})
@PropertySource("classpath:/application.properties")
@EnableWebMvc
//@PropertySource("classpath:/someOtherFile.properties")
public class ApplicationConfiguration {

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

    // we cannot use @Bean for getting object mapper bean as it is not a class that we wrote
    @Bean
    @Scope("singleton")
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

//    @Bean // de-comment if you are using validation in @RequestParam
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
}
