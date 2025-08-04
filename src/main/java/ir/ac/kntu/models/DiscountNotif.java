package ir.ac.kntu.models;

public class DiscountNotif extends Notification{

    private Discount discount;

    public DiscountNotif(String massage, String senderUsername, Discount discount) {
        super(massage, senderUsername);
        this.discount = discount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountNotif{" +
                "discount=" + discount +
                '}';
    }
}
