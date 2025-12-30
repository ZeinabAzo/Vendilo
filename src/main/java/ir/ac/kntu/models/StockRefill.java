package ir.ac.kntu.models;

public class StockRefill extends Notification{

    private Product product;

    public StockRefill(String massage, String senderUsername, Product product) {
        super(massage, senderUsername);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
