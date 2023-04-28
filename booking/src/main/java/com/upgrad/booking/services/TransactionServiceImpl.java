package com.upgrad.booking.services;

import com.upgrad.booking.dao.BookingDAO;
import com.upgrad.booking.dto.PaymentDTO;
import com.upgrad.booking.dto.TransactionIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    public RestTemplate restTemplate;

    public TransactionServiceImpl() {
    }

    @Override
    public int getTransactionNumber(PaymentDTO paymentDTO) {
        String paymentUri = "http://localhost:8082/transaction";
        TransactionIdDTO transactionIdDTO = this.restTemplate.postForObject(paymentUri, paymentDTO, TransactionIdDTO.class, new Object[0]);

        return transactionIdDTO == null ? 0 : transactionIdDTO.getTransactionId();
    }
}
