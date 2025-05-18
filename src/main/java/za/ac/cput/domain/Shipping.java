package za.ac.cput.domain;
/*
Shipping.java
Shipping model class
Author: Tsholofelo Mabidikane (230018165)
Date: 15 March 2025
 */

import java.time.LocalDateTime;

public class Shipping {

    private int id;
    private int orderId;
    private String address;
    private String status;
    private LocalDateTime estimatedDeliveryDate;
    private String trackingNumber;

    private Shipping () {
    }

    private Shipping(Builder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.address = builder.address;
        this.status = builder.status;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.trackingNumber = builder.trackingNumber;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }

    public static class Builder {
        private int id;
        private int orderId;
        private String address;
        private String status;
        private LocalDateTime estimatedDeliveryDate;
        private String trackingNumber;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
            this.estimatedDeliveryDate = estimatedDeliveryDate;
            return this;
        }

        public Builder setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public Builder copy(Shipping shipping) {
            this.id = shipping.id;
            this.orderId = shipping.orderId;
            this.address = shipping.address;
            this.status = shipping.status;
            this.estimatedDeliveryDate = shipping.estimatedDeliveryDate;
            this.trackingNumber = shipping.trackingNumber;
            return this;
        }

        public Shipping build() {
            return new Shipping(this);
        }

    }
}
