package com.ishan.web;

import com.ishan.dto.InvoiceDto;
import com.ishan.model.Invoice;
import com.ishan.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @Validated // de-comment if you are using validation through @RequestParam
public class InvoicesController {

    private InvoiceService invoiceService;

    public InvoicesController(@Autowired InvoiceService invoiceService){
        this.invoiceService = invoiceService;
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
//    public Invoice addInvoice(@RequestParam("user_id") @NotBlank String userId, @RequestParam("amount") int amount){
//        Invoice newlyCreatedInvoice = invoiceService.createInvoice(userId, amount);
//        return newlyCreatedInvoice;
//    }

      @PostMapping("/add-invoice")
      public Invoice addInvoice(@RequestBody @Valid InvoiceDto invoiceDto){
        Invoice newlyCreatedInvoice = invoiceService.createInvoice(invoiceDto.getUserId(), invoiceDto.getAmount());
        return newlyCreatedInvoice;
      }
}
