package com.ishan.pdf.invoice.generator.web;

import com.ishan.pdf.invoice.generator.dto.InvoiceDto;
import com.ishan.pdf.invoice.generator.model.Invoice;
import com.ishan.pdf.invoice.generator.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(@Autowired InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add-invoice")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto){
        String userId = invoiceDto.getUserId();
        int amount = invoiceDto.getAmount();
        Invoice newlyCreatedInvoice = invoiceService.createInvoice(userId,amount);
        return newlyCreatedInvoice;
    }

    @GetMapping("/show-invoices")
    public List<Invoice> getInvoices(){
        List<Invoice>  listOfAllInvoices = invoiceService.findAllInvoices();
        return listOfAllInvoices;
    }
}
