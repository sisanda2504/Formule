package za.ac.cput.domain;

import java.util.List;

public class Cart {
    private int id;
    private Customer customer;
    private List<CartItems> items;
    private Double totalPrice;

    private Cart(Cart cart) {
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
        private int id;
        private Customer customer;
        private List<CartItems> items;
        private Double totalPrice;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return null;
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