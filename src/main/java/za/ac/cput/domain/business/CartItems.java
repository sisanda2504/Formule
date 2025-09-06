package za.ac.cput.domain.business;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private Cart cart;

    private int quantity;

    protected CartItems() {}

    private CartItems(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.cart = builder.cart;
        this.quantity = builder.quantity;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Cart getCart() {
        return cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemTotal() {
        if (product == null || quantity == 0) {
            return 0;
        }
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", product=" + (product != null ? product.getId() : null) +
                ", quantity=" + quantity +
                ", itemTotal=" + getItemTotal() +
                '}';
    }

    public static class Builder {
        private Long id;
        private Product product;
        private Cart cart;
        private int quantity;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(CartItems cartItems) {
            this.id = cartItems.id;
            this.product = cartItems.product;
            this.cart = cartItems.cart;
            this.quantity = cartItems.quantity;
            return this;
        }

        public CartItems build() {
            return new CartItems(this);
        }
    }
}
