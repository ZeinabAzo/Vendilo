package ir.ac.kntu.models;

public class Product {

    private String name;
    private double price;
    private int inventory;
    private Seller seller;

    public Product(){
    }

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

    public boolean setInventory(int inventory) {
        if(inventory>=0) {
            this.inventory = inventory;
            return true;
        }
        return false;
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
