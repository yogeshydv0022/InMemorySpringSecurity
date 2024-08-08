package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.dto.PayDto;
import com.model.dto.PaymentDto;

@Service
public class ApplicationService {

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    public PaymentDto initiatePayment(PayDto payDto) throws Exception {
        return paymentServiceClient.createPayment(payDto);
    }

    public PaymentDto processPaymentCallback(String razorpayOrderId) throws Exception {
        return paymentServiceClient.handlePaymentCallback(razorpayOrderId);
    }
}
