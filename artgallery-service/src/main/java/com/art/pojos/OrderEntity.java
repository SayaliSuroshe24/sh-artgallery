package com.art.pojos;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "cust_id")
    private User customer;
    private Date orderDate;
    private double totalAmount;
    private String orderStatus;

//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems;
//
    @OneToOne//(mappedBy = "order")
    private Payment payment;

    @OneToOne//(mappedBy = "order")
    private Shipping shipping;
}