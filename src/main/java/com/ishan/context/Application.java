package com.ishan.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishan.service.InvoiceService;
import com.ishan.service.UserService;

public class Application {

    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    // this is dependency injection, the object of the InvoiceService class needs an object of the
    // UserService class but the object of the UserService class is not given that explicitly bahar se
    // Instead, this dependency is injected into the Invoice service class in the Application class
    public static final ObjectMapper objectMapper = new ObjectMapper();



}
