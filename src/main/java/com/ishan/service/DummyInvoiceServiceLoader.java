package com.ishan.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service // carries the same technical meaning as @Componenet, just semantically clarifies that this is indeed a service
@Profile("dev")
public class DummyInvoiceServiceLoader {

    private final InvoiceService invoiceService;

    public DummyInvoiceServiceLoader( @Autowired InvoiceService invoiceService ){
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void startup(){
        System.out.println("We are currently in the dev environment");
        invoiceService.createInvoice("ohWeMadeIt",54);
        invoiceService.createInvoice("aBusinessKindOfWomen",108);
    }
}
