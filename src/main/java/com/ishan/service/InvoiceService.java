package com.ishan.service;

import com.ishan.context.Application;
import com.ishan.model.Invoice;
import com.ishan.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    private final UserService userService;
    private final List<Invoice> listOfAllInvoices;

    public InvoiceService(UserService userService)
    {
        this.userService = userService;
        this.listOfAllInvoices = new CopyOnWriteArrayList<Invoice>();
    }
    // this is dependency injection, the object of the InvoiceService class needs an object of the
    // UserService class but the object of the UserService class is not given that explicitly bahar se
    // Instead, this dependency is injected into the Invoice service class in the Application class

    public List<Invoice> findAllInvoices()
    {
        return this.listOfAllInvoices;
    }

    public Invoice createInvoice( String userId, int amount )
    {
        User userAssociatedWithGivenUserId = this.userService.findUserById(userId);
        boolean userExists = (userAssociatedWithGivenUserId != null);

        if( !userExists ){
            throw new IllegalStateException();
        }

        // if user exists
        Invoice newInvoice = new Invoice(userId, amount, "https://www.clickdimensions.com/links/TestPDFfile.pdf");
        this.listOfAllInvoices.add(newInvoice);
        return newInvoice;
    }

}
