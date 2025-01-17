package com.food.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderHistory {
    private int orderHistoryId;
    private int userId;
    private int orderId;
    private Date orderDate;
    private double totalAmount;
    private String Status;


}
