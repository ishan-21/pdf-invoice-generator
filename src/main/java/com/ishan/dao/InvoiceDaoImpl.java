package com.ishan.dao;

import com.ishan.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Component
public class InvoiceDaoImpl implements InvoiceDao{

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Invoice> rowMapper;

    public InvoiceDaoImpl(@Autowired JdbcTemplate jdbcTemplate, @Autowired RowMapper<Invoice> rowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    @Transactional
    public void insertInvoiceInDatabase(Invoice invoice) {
        System.out.println("Is a database transaction open? = " + (TransactionSynchronizationManager.isActualTransactionActive() ? "yes" : "no"));
        String sqlQuery = "insert into " + TABLE_NAME + "(id,user_id,pdf_url,amount) values(?,?,?,?)";
        jdbcTemplate.update(sqlQuery, invoice.getInvoiceId(), invoice.getUserId(), invoice.getPdfUrl(), invoice.getAmount());
    }

    @Override
    @Transactional
    public List<Invoice> getAllInvoicesFromDatabase() {
        System.out.println("Is a database transaction open? = " + (TransactionSynchronizationManager.isActualTransactionActive() ? "yes" : "no"));
        String sqlQuery = "select * from " + TABLE_NAME;
        List<Invoice> listOfAllInvoices = jdbcTemplate.query(sqlQuery, rowMapper);
        return listOfAllInvoices;
    }

    @Override
    public void clearAllRecordsFromDatabase() {
        String sqlQuery = "delete from " + TABLE_NAME;
        jdbcTemplate.update(sqlQuery);
    }

}
