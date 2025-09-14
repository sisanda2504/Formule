package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.repository.business.CartItemsRepository;
import za.ac.cput.repository.business.CartRepository;
import za.ac.cput.repository.business.ProductRepository;
import za.ac.cput.repository.users.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart read(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

    public boolean delete(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart findByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }


    public CartItems addToCart(Long customerId, Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        // Find or create cart
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart == null) {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            cart = new Cart.Builder()
                    .setCustomer(customer)
                    .build();
            cart = cartRepository.save(cart);
        }

        // Find product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if item already in cart
        Optional<CartItems> existingItem = cartItemsRepository.findByCartAndProduct(cart, product);
        if (existingItem.isPresent()) {
            CartItems item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity;

           //Update quantity
            CartItems updatedItem = new CartItems.Builder()
                    .copy(item)
                    .setQuantity(newQuantity)
                    .build();
            return cartItemsRepository.save(updatedItem);

        } else {
            // Create new item
            CartItems newItem = new CartItems.Builder()
                    .setCart(cart)
                    .setProduct(product)
                    .setQuantity(quantity)
                    .build();
            return cartItemsRepository.save(newItem);
        }
    }


    public boolean removeFromCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart == null) {
            return false; // Cart doesn't exist
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItems> item = cartItemsRepository.findByCartAndProduct(cart, product);
        if (item.isPresent()) {
            cartItemsRepository.delete(item.get());
            return true;
        }
        return false;
    }
}