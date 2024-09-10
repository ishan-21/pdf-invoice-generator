package com.ishan.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishan.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.ishan.model.Invoice;
import com.ishan.service.InvoiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class MyFancyPdfInvoicesServlet extends HttpServlet {

    private InvoiceService invoiceService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);

        ctx.registerShutdownHook();

        invoiceService = ctx.getBean(InvoiceService.class);
        objectMapper = ctx.getBean(ObjectMapper.class);
    }

    public MyFancyPdfInvoicesServlet(){

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String requestUrl = request.getRequestURI();
        PrintWriter out = response.getWriter();
        if( requestUrl.equalsIgnoreCase("/")){
            response.setContentType("text/html; charset=UTF-8");
            out.println("<h1>Hello World</h1>\n" + "This is my very first, embedded Tomcat, HTML Page!");
        }else if( requestUrl.equalsIgnoreCase("/invoices")){
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> listOfAllInvoices = invoiceService.findAllInvoices();
            String jsonString = objectMapper.writeValueAsString(listOfAllInvoices);
            out.println(jsonString);
        }else{
            // do nothing
        }

    }

    // we are using postman at this point to take in parameters through the
    // post request => userId and amount
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestUrl = request.getRequestURI();

        if( requestUrl.equalsIgnoreCase("/invoices"))
        {
            String userId = request.getParameter("userId");
            int amount = Integer.parseInt(request.getParameter("amount"));

            Invoice objectOfInvoiceClass = invoiceService.createInvoice(userId, amount);
            String jsonInvoiceAsString = objectMapper.writeValueAsString(objectOfInvoiceClass);

            PrintWriter out = response.getWriter();
            out.print(jsonInvoiceAsString);
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
