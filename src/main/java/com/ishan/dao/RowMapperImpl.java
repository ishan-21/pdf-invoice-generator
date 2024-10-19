package com.ishan.dao;

import com.ishan.model.Invoice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

// row mapper is used to map the database rows to java objects
@Component
public class RowMapperImpl implements RowMapper<Invoice>{
    @Override
    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getString("id"));
        invoice.setUserId(rs.getString("user_id"));
        invoice.setPdfUrl(rs.getString("pdf_url"));
        invoice.setAmount(rs.getInt("amount"));

        return invoice;
    }
}



