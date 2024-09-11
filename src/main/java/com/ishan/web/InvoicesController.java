package com.ishan.web;

import com.ishan.dto.InvoiceDto;
import com.ishan.model.Invoice;
import com.ishan.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @Validated  // de-comment if using validation though @RequestParam
//@Controller   // RestController = Controller + ResponseBody
//@ResponseBody
public class InvoicesController {

    private final InvoiceService invoiceService;

    // private ObjectMapper objectMapper; => we do not need to convert objects to json now
    // as we have used @ResponseBody => everything we will return will be written on screen

    public InvoicesController(@Autowired InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/show-invoices")
    // @RequestMapping(value = "/show-invoices", method = RequestMethod.GET)
    // we could also have used request mapping like mentioned above
    public List<Invoice> displayAllInvoices() {
        List<Invoice> listOfAllInvoices = invoiceService.findAllInvoices();
        return listOfAllInvoices;
    }

//    @PostMapping("/add-invoice")
//    if the user_id and amount are being sent in as parameters in the POST request
//    POST http://localhost:8080/add-invoice/?amount=5000&user_id=someId
//    // @RequestMapping(value = "/add-invoice", method = RequestMethod.POST)
//    public Invoice createInvoice(@RequestParam("userId") @NotBlank  String userId, @RequestParam("amount") @PositiveOrZero Integer amount) {
//        Invoice newlyCreatedInvoice = invoiceService.createInvoice(userId, amount);
//
//        return newlyCreatedInvoice;
//    }

//    an alternative to using @RequestMapping as above
//    @PostMapping("/add-invoice/{userId}/{amount}")
//    if the user_id and amount are being sent in as parameters in the POST request
//    POST http://localhost:8080/add-invoice/someId/5000
//    public Invoice createInvoice(@PathVariable String userId, @PathVariable Integer amount) {
//        return invoiceService.createInvoice(userId, amount);
//    }



    /*
        if the user_id and amount are being sent in as json in the body of the POST request
        POST http://localhost:8080/invoices
        Content-Type: application/json
          ####
        {
          "amount": 5000,
          "user_id": "helene"
         }
         // we have modeled the above json as a data transfer object class (dto) as InvoiceDto
     */
    @PostMapping("/add-invoice")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        Invoice newlyCreatedInvoice = invoiceService.createInvoice(invoiceDto.getUserId(), invoiceDto.getAmount());
        return newlyCreatedInvoice;
    }

}
