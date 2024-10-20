package com.ishan.pdf.invoice.generator.dao;

import com.ishan.pdf.invoice.generator.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    public static final String TABLE_NAME = "invoices";

    public void insertInvoiceInDatabase(Invoice invoice);

    public List<Invoice> getAllInvoicesFromDatabase();

    public void clearAllRecordsFromDatabase();
}
