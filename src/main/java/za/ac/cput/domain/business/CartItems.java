package za.ac.cput.domain.business;


class CartItems {
    private Product product;
    private int quantity;
    private Double totalItems;

    private CartItems(){}
    private CartItems(Builder builder){
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.totalItems = builder.totalItems;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getTotalItems() {
        return totalItems;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", totalItems=" + totalItems +
                '}';
    }
    public static class Builder{
        private Product product;
        private int quantity;
        private Double totalItems;

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setTotalItems(Double totalItems) {
            this.totalItems = totalItems;
            return this;
        }
        public Builder copy(CartItems cartItems) {
            this.product = cartItems.product;
            this.quantity = cartItems.quantity;
            this.totalItems = cartItems.totalItems;
            return this;
        }
        public CartItems build(){
            return new CartItems(this);
        }
    }
}

