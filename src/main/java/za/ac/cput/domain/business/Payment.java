/*
Author: Annah Gaula Manda (230164250)
Date: 20/04/2025
 */

package za.ac.cput.domain.business;
import za.ac.cput.domain.users.Customer;

import java.util.Date;

public class Payment {
    private int Id;
    private Customer customer;
    private double amount;
    private PaymentMethod method;
    private Date date;
    private Status status;


    private Payment() {}


    private Payment(Builder builder) {
        this.Id = builder.Id;
        this.customer = builder.customer;
        this.amount = builder.amount;
        this.method = builder.method;
        this.date = builder.date;
        this.status = builder.status;
    }


    public int getId() {
        return Id;
    }


    public Customer getCustomer() {
        return customer;
    }


    public double getAmount() {
        return amount;
    }


    public PaymentMethod getMethod() {
        return method;
    }


    public Date getDate() {
        return date;
    }


    public Status getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "Id=" + Id +
                ", customer=" + customer +
                ", amount=" + amount +
                ", method=" + method +
                ", date=" + date +
                ", status=" + status +
                '}';
    }


    public static class Builder {
        private int Id;
        private Customer customer;
        private double amount;
        private PaymentMethod method;
        private Date date;
        private Status status;


        public Builder setId(int Id) {
            this.Id = Id;
            return this;
        }


        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }


        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }


        public Builder setMethod(PaymentMethod method) {
            this.method = method;
            return this;
        }


        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }


        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }


        public Builder copy(Payment payment) {
            this.Id = payment.getId();
            this.customer = payment.getCustomer();
            this.amount = payment.getAmount();
            this.method = payment.getMethod();
            this.date = payment.getDate();
            this.status = payment.getStatus();
            return this;
        }


        public Payment build() {
            return new Payment(this);
        }
    }
}

