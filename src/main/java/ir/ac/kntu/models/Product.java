package ir.ac.kntu.models;

public abstract class Product {

    private String name;
    private double price;
    private int inventory;
    private Seller seller;

    public Product(String name, double price, int inventory, Seller seller){
        this.name=name;
        this.price=price;
        this.inventory=inventory;
        this.seller=seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public boolean sellProduct(){
        if(inventory>0) {
            inventory -= 1;
            return true;
        }
        return false;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public Seller getSeller() {
        return seller;
    }
}
