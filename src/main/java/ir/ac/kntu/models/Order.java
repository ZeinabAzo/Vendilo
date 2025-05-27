package ir.ac.kntu.models;

import java.time.LocalDate;

import static ir.ac.kntu.util.PrintHelper.printError;


public class Order {

    private Product product;
    private Seller seller;
    private LocalDate orderDate;

    public Order(Product product, Seller seller, LocalDate orderDate) {
        this.product = product;
        this.seller = seller;
        this.orderDate = orderDate;
    }

    public Order createOrder(Product product, Address shippingAddress, Cart cart) {
        if (product == null ||  shippingAddress == null) {
            printError("The entered data was not recognized.");
            return null;
        }

        if (product.getInventory() <= 0) {
            printError("Product is out of stock.");
            return null;
        }

        Order newOrder = new Order(product, seller , LocalDate.now());
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

    @Override
    public String toString() {
        return "Order {" +
                "\n  Product: " + (product != null ? product : "N/A") +
                ",\n  Seller: " + (seller != null ? seller.getfName() : "N/A") +
                ",\n  Order Date: " + (orderDate != null ? orderDate : "N/A") +
                "\n}";
    }


}
