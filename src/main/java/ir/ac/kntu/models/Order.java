package ir.ac.kntu.models;

import java.time.LocalDate;
import static ir.ac.kntu.util.PrintHelper.printError;


public class Order {

    private Product product;
    private Customer customer;
    private Seller seller;
    private LocalDate orderDate;

    public Order(Product product, Customer customer, Seller seller, LocalDate orderDate) {
        this.product = product;
        this.customer = customer;
        this.seller = seller;
        this.orderDate = orderDate;
    }

    public static Order createOrder(Product product, Customer customer, Address shippingAddress, Cart cart) {
        if (product == null || customer == null || shippingAddress == null) {
            printError("The entered data was not recognized.");
            return null;
        }

        if (product.getInventory() <= 0) {
            printError("Product is out of stock.");
            return null;
        }

        if (customer.getCart(cart) == null) {
            printError("The entered cart was not recognized.");
            return null;
        }

        Order newOrder = new Order(product, customer, product.getSeller(), LocalDate.now());
        customer.getCart(cart).addToCart(newOrder);
        product.sellProduct();

        return newOrder;
    }

    // Getters and setters...
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

}
