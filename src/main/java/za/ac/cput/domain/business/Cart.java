package za.ac.cput.domain.business;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import za.ac.cput.domain.users.Customer;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CartItems> items = new ArrayList<>();

    protected Cart() {}

    private Cart(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.items = builder.items != null ? builder.items : new ArrayList<>();
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<CartItems> getItems() { return items; }
    
    public List<CartItems> getCartItems() {
        return getItems();
    }
    
    public Long getCustomerId() { return customer != null ? customer.getId() : null; }

    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItems::getItemTotal).sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + getCustomerId() +
                ", items=" + items.size() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }

    public static class Builder {
        private Long id;
        private Customer customer;
        private List<CartItems> items;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setCustomer(Customer customer) { this.customer = customer; return this; }
        public Builder setItems(List<CartItems> items) { this.items = items != null ? items : new ArrayList<>(); return this; }

        public Builder copy(Cart cart) {
            this.id = cart.getId();
            this.customer = cart.getCustomer();
            this.items = cart.getItems();
            return this;
        }

        public Cart build() { return new Cart(this); }
    }
}