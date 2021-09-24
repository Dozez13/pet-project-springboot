package com.example.task16.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Order entity
 *
 * @author Pavlo Manuilenko
 */
@Entity
@Table(name = "user_order", indexes = {
        @Index(name = "idx_order_userid_carid_unq", columnList = "userId, carId", unique = true)
})
@Getter
@Setter
public class Order implements Serializable {
    private static final long serialVersionUID = -4577879200654802752L;
    @Id
    @GeneratedValue
    @Column(name = "orderid",columnDefinition = "BINARY(16)")
    private UUID orderId ;
    @Column(name = "useraddress", length = 60)
    private String userAddress;
    @Column(name = "userdestination", length = 60)
    private String userDestination;
    @Column(name = "ordercost")
    private double orderCost;
    @Column(name = "orderdate", columnDefinition = "DATETIME")
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carid")
    private Car car;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", userAddress='").append(userAddress).append('\'');
        sb.append(", userDestination='").append(userDestination).append('\'');
        sb.append(", orderCost=").append(orderCost);
        sb.append(", orderDate=").append(orderDate);
        sb.append('}');
        return sb.toString();
    }
}
