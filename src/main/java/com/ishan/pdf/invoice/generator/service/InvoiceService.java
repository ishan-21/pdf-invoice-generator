package com.ishan.pdf.invoice.generator.service;

import com.ishan.pdf.invoice.generator.dao.InvoiceDao;
import com.ishan.pdf.invoice.generator.model.Invoice;
import com.ishan.pdf.invoice.generator.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Scope("singleton")
public class InvoiceService {

    private final UserService userService;
    private final String cdnUrl;
    private final InvoiceDao invoiceDao;

    public InvoiceService(@Autowired UserService userService,
                          @Value("${cdn.url}") String cdnUrl,
                          @Autowired  InvoiceDao invoiceDao){
        // the value of cdnUrl will be picked up from here
        // ${cdn.url} will be read from the application.properties file mentioned in the @PropertySource
        // annotation in the configuration class
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.invoiceDao = invoiceDao;
    }

    @PostConstruct
    public void initBean(){
        System.out.println("Fetching the template for pdf from Amazon S3......");
    }

    @PreDestroy
    public void destroyBean(){
        System.out.println("Deleting the fetched templates from the local cache......");
    }

//    public InvoiceService(UserService userService)
//    {
//        this.userService = userService;
//        this.listOfAllInvoices = new CopyOnWriteArrayList<Invoice>();
//    }

    // this is dependency injection, the object of the InvoiceService class needs an object of the
    // UserService class but the object of the UserService class is not given that explicitly bahar se
    // Instead, this dependency is injected into the Invoice service class in the Application class

    public List<Invoice> findAllInvoices()
    {
        return invoiceDao.getAllInvoicesFromDatabase();
    }

    public void deleteAllInvoices()
    {
        invoiceDao.clearAllRecordsFromDatabase();
    }

    public Invoice createInvoice( String userId, int amount )
    {
        User userAssociatedWithGivenUserId = userService.findUserById(userId);
        boolean userExists = (userAssociatedWithGivenUserId != null);

        if( !userExists ){
            throw new IllegalStateException();
        }

        // if user exists

        // REAL PDF CREATION AND STORING IT IN THE CDN

        Invoice newInvoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoiceDao.insertInvoiceInDatabase(newInvoice);
        return newInvoice;
    }

}