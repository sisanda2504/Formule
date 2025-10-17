package za.ac.cput.domain.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import za.ac.cput.domain.users.Customer;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Order() {}

    private Order(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.orderDate = builder.orderDate;
        this.totalAmount = builder.totalAmount;
        this.orderItems = builder.orderItems;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Long getCustomerId() {
        return customer != null ? customer.getId() : null;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + (customer != null ? customer.getId() : null) +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", orderItems=" + orderItems.size() +
                '}';
    }

    public static class Builder {
        private Long id;
        private Customer customer;
        private LocalDate orderDate;
        private Double totalAmount;
        private List<OrderItem> orderItems = new ArrayList<>();

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
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

        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder copy(Order order) {
            this.id = order.id;
            this.customer = order.customer;
            this.orderDate = order.orderDate;
            this.totalAmount = order.totalAmount;
            this.orderItems = order.orderItems;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}