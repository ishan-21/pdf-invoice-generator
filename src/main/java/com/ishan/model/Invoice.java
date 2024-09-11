package com.ishan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;


public class Invoice {

    @JsonProperty("invoice_id")
    private final String invoiceId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("url_pdf")
    private String pdfUrl;

    public Invoice(String userId, int amount, String pdfUrl) {
        this.invoiceId = UUID.randomUUID().toString();
        this.userId = userId;
        this.amount = amount;
        this.pdfUrl = pdfUrl;
    }

    public Invoice(){
        super();
        this.invoiceId = UUID.randomUUID().toString();
    }

    public String toString(){
        return "INVOICE ID: " + invoiceId +  " USER ID: " + userId + " AMOUNT: " + amount + " PDF URL: " + pdfUrl;
    }

    // invoiceId is final so there is no setter for that 
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getUserId() {
        return userId;
    }

    public int getAmount() {
        return amount;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

}
