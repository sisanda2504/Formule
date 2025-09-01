package za.ac.cput.domain.business;


import jakarta.persistence.*;

@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false )
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false )
    private Cart cart;

    private int quantity;
    @Column(name="item_total")
    private Double itemTotal;

    protected CartItems(){}
    private CartItems(Builder builder){
        this.id = builder.id;
        this.product = builder.product;
        this.cart = builder.cart;
        this.quantity = builder.quantity;
        this.itemTotal = builder.itemTotal;
    }

    public Long getId() { return id;}

    public Product getProduct() {
        return product;
    }

    public Cart getCart() {return cart; }

    public int getQuantity() {
        return quantity;
    }

    public Double getItemTotal() {
        return itemTotal;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", product=" + product +
                ", cart=" + cart +
                ", quantity=" + quantity +
                ", itemTotal=" + itemTotal +
                '}';
    }

    public static class Builder{
        private Long id;
        private Product product;
        private Cart cart;
        private int quantity;
        private Double itemTotal;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }


        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setItemTotal(Double itemTotal) {
            this.itemTotal = itemTotal;
            return this;
        }

        public Builder copy(CartItems cartItems) {
            this.id = cartItems.id;
            this.product = cartItems.product;
            this.cart = cartItems.cart;
            this.quantity = cartItems.quantity;
            this.itemTotal = cartItems.itemTotal;
            return this;
        }
        public CartItems build(){
            return new CartItems(this);
        }
    }
}

