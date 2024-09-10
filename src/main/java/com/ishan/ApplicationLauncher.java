package com.ishan;

import com.ishan.context.Application;
import com.ishan.web.MyFancyPdfInvoicesServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException
    {

        Tomcat tomcat = new Tomcat();
        // instantiating a new tomcat

        tomcat.setPort(8080);
        // setting the port of the servlet container (tomcat) to 8080

        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);

        Wrapper servlet = Tomcat.addServlet(ctx, "myFancyPdfInvoicesServlet", new MyFancyPdfInvoicesServlet());

        // servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
        System.out.println("The server is now running.");
    }
}
