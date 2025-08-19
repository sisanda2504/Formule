package za.ac.cput.domain.business;



import jakarta.persistence.*;
import za.ac.cput.domain.CartItems;
import za.ac.cput.domain.users.Customer;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)  // A cart must have a customer
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItems> items;

    @Column(name = "total_price")
    private double totalPrice;

    // Empty constructor required by JPA
    protected Cart() {
    }

    private Cart(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.items = builder.items;
        this.totalPrice = builder.totalPrice;

    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private Integer id;
        private Customer customer;
        private List<CartItems> items;
        private Double totalPrice;


        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setItems(List<CartItems> items) {
            this.items = items;
            return this;
        }

        public Builder setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;

            return this;
        }

        public Builder copy(Cart cart) {
            this.id = cart.getId();
            this.customer = cart.getCustomer();
            this.items = cart.getItems();
            this.totalPrice = cart.getTotalPrice();
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }

    }
}
