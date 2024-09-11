package com.ishan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("amount")
    private int amount;

    public String getUserId() {
        return userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
