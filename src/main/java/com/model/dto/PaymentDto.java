package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Integer id;
    private String name;
    private String email;
    private Integer amount;
    private String status;
    private String razorpayOrderId;
}
