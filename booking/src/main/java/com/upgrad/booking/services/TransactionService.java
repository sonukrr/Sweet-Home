package com.upgrad.booking.services;

import com.upgrad.booking.dto.PaymentDTO;

public interface TransactionService {
    int getTransactionNumber(PaymentDTO paymentDTO);
}
