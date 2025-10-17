package za.ac.cput.domain.business;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;
    private double itemTotal;

    protected OrderItem() {}

    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.itemTotal = builder.itemTotal;
    }

    public Long getId() { return id; }
    public Order getOrder() { return order; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getItemTotal() { return itemTotal; }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + (order != null ? order.getId() : null) +
                ", product=" + (product != null ? product.getId() : null) +
                ", quantity=" + quantity +
                ", itemTotal=" + itemTotal +
                '}';
    }

    // ====================== BUILDER ======================
    public static class Builder {
        private Long id;
        private Order order;
        private Product product;
        private int quantity;
        private double itemTotal;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setOrder(Order order) { this.order = order; return this; }
        public Builder setProduct(Product product) { this.product = product; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }
        public Builder setItemTotal(double itemTotal) { this.itemTotal = itemTotal; return this; }

        public Builder copy(OrderItem orderItem) {
            this.id = orderItem.getId();
            this.order = orderItem.getOrder();
            this.product = orderItem.getProduct();
            this.quantity = orderItem.getQuantity();
            this.itemTotal = orderItem.getItemTotal();
            return this;
        }

        public OrderItem build() { return new OrderItem(this); }
    }
}