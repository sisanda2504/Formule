package za.ac.cput.domain;
/*
Order.java
Order model class
Author: Tsholofelo Mabidikane (230018165)
Date: 15 March 2025
 */

import java.time.LocalDateTime;
import java.util.Date;

public class Order {

    private int id;
    private int customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;

    private Order() {
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.orderDate = builder.orderDate;
        this.totalAmount = builder.totalAmount;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public static class Builder {
        private int id;
        private int customerId;
        private LocalDateTime orderDate;
        private Double totalAmount;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder copy(Order order) {
            this.id = order.id;
            this.customerId = order.customerId;
            this.orderDate = order.orderDate;
            this.totalAmount = order.totalAmount;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
