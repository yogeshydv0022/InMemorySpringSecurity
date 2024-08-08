package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.dto.PayDto;
import com.model.dto.PaymentDto;

@FeignClient(name = "payment-service",url="http://localhost:8081/payments")
public interface PaymentServiceClient {
    
	    @PostMapping("/pay")
	    PaymentDto createPayment(@RequestBody PayDto payDto);

	    @PostMapping("/callback/")
	    PaymentDto handlePaymentCallback(@RequestParam String razorpayOrderId);
	}
	


