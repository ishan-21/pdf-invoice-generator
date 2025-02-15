package com.ishan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishan.constants.Constants;
import com.ishan.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.ishan.service.InvoiceService;
import com.ishan.web.MyFancyPdfInvoicesServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationLauncher
{

    private static Tomcat createTomcatInstance( int portNumber ){
        Tomcat tomcatInstance = new Tomcat();

        tomcatInstance.setPort(portNumber);
        // setting the port of the servlet container (tomcat)

        tomcatInstance.getConnector();

        return tomcatInstance;
    }

    public static ApplicationContext createSpringApplicationContext(Class springConfigurationClass){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(springConfigurationClass);

        applicationContext.registerShutdownHook();

        return applicationContext;
    }

    public static void main(String[] args) throws LifecycleException
    {
        Tomcat tomcatInstance = createTomcatInstance(Constants.DEFAULT_PORT_NUM);

        Context servletContainerContext =  tomcatInstance.addContext("", null);

        ApplicationContext springApplicationContext = createSpringApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);

        Wrapper servlet = Tomcat.addServlet(servletContainerContext, "myFancyPdfInvoicesServlet", new MyFancyPdfInvoicesServlet(springApplicationContext.getBean(InvoiceService.class),springApplicationContext.getBean(ObjectMapper.class)));

        // servlet.setLoadOnStartup(1); => if this was not commented then the servlet would get created
        servlet.addMapping("/*");

        tomcatInstance.start();

        System.out.println("The server is now running.");
    }
}
