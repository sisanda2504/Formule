/*
Shipping.java
Shipping model class
Author: Tsholofelo Mabidikane (230018165)
Date: 15 March 2025
 */
package za.ac.cput.domain.business;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "estimated_delivery_date", nullable = false)
    private LocalDate estimatedDeliveryDate;

    @Column(name = "tracking_number", unique = true)
    private String trackingNumber;

    protected Shipping() {}

    private Shipping(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.address = builder.address;
        this.status = builder.status;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.trackingNumber = builder.trackingNumber;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", order=" + order +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private Order order;
        private String address;
        private Status status;
        private LocalDate estimatedDeliveryDate;
        private String trackingNumber;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
            this.estimatedDeliveryDate = estimatedDeliveryDate;
            return this;
        }

        public Builder setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public Builder copy(Shipping shipping) {
            this.id = shipping.id;
            this.order = shipping.order;
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
