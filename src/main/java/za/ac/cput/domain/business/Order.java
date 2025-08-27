/*
Order.java
Order model class
Author: Tsholofelo Mabidikane (230018165)
Date: 15 March 2025
 */
package za.ac.cput.domain.business;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {
    @Id
    private int id;
    private int customerId;
    private LocalDate orderDate;
    private Double totalAmount;

    protected Order() {
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

    public LocalDate getOrderDate() { return orderDate; }

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
        private LocalDate orderDate;
        private Double totalAmount;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setOrderDate(LocalDate orderDate) {
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
