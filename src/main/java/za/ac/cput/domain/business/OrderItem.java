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

    public OrderItem(Order order, Product product, int quantity, double itemTotal) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemTotal() {
        return itemTotal;
    }
}