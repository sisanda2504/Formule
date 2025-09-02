package za.ac.cput.domain.business;



import jakarta.persistence.*;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.users.Customer;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItems> items;

    @Column(name = "total_price")
    private double totalPrice;

    protected Cart() {
    }

    private Cart(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.items = builder.items;
        this.totalPrice = builder.totalPrice;

    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public double getTotalPrice() {
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
        private Long id;
        private Customer customer;
        private List<CartItems> items;
        private double totalPrice;


        public Builder setId(Long id) {
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
