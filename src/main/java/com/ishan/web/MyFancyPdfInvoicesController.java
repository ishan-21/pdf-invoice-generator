package com.ishan.web;

import com.ishan.dto.InvoiceDto;
import com.ishan.model.Invoice;
import com.ishan.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyFancyPdfInvoicesController {

    private InvoiceService invoiceService;

    public MyFancyPdfInvoicesController(@Autowired InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public String homePage(){
        String response = "<h1>Hello World</h1>\n" + "This is my very first, embedded Tomcat, HTML Page!";
        return response;
    }

    @GetMapping("/show-invoices")
    public List<Invoice> getAllInvoices(){
        List<Invoice> listOfAllInvoices = invoiceService.findAllInvoices();
        return listOfAllInvoices;
    }

//    @PostMapping("/add-invoice/{userId}/{amount}")
//    public Invoice createInvoice(@PathVariable String userId, @PathVariable Integer amount) {
//        return invoiceService.createInvoice(userId, amount);
//    }

//    @PostMapping("/add-invoice")
//    public Invoice addInvoice(@RequestParam("user_id") String userId, @RequestParam("amount") int amount){
//        Invoice newlyCreatedInvoice = invoiceService.createInvoice(userId, amount);
//        return newlyCreatedInvoice;
//    }

      @PostMapping("/add-invoice")
      public Invoice addInvoice(@RequestBody InvoiceDto invoiceDto){
        Invoice newlyCreatedInvoice = invoiceService.createInvoice(invoiceDto.getUserId(), invoiceDto.getAmount());
        return newlyCreatedInvoice;
      }
}
