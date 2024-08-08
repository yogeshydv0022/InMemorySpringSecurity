package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.dto.PayDto;
import com.model.dto.PaymentDto;
import com.service.ApplicationService;


@RestController
@RequestMapping("/main")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/payment")
    public ResponseEntity<?> startPayment(@RequestBody PayDto payDto) {
        try {
            PaymentDto payment = applicationService.initiatePayment(payDto);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error initiating payment: " + e.getMessage());
        }
    }
    
    @PostMapping("/callback")
    public ResponseEntity<?> paymentCallback(@RequestParam String razorpayOrderId) {
        try {
            PaymentDto payment = applicationService.processPaymentCallback(razorpayOrderId);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing payment callback: " + e.getMessage());
        }
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getMethodName() {
        return "This is User Testing Endpoint";
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
    public String getName() {
        return "This is Admin Testing Endpoint";
    }
    
    @GetMapping("/both")
    public String getboth() {
        return "This is ADMIN & USER  using endpoint ";
    }
    
    
}
