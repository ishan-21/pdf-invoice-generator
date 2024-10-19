package com.ishan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.NotBlank;

// A DTO (data transfer object) in java
// These classes are solely used to transfer data between the frontend and your backend
// In this case, we are taking the userId and the amount through the POST request
// through the url "/add-invoice"
public class InvoiceDto {

    @NotBlank
    @JsonProperty("user_id")
    private String userId;

    @PositiveOrZero
    @JsonProperty("amount")
    private Integer amount;

    public String getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
