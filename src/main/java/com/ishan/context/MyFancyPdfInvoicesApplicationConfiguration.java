package com.ishan.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishan.model.User;
import com.ishan.service.InvoiceService;
import com.ishan.service.UserService;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.ishan"})
@PropertySource("classpath:/application.properties")
@EnableWebMvc
//@PropertySource("classpath:/someOtherFile.properties")
public class MyFancyPdfInvoicesApplicationConfiguration {

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
}
