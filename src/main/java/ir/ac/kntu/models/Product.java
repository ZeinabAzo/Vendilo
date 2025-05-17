package ir.ac.kntu.models;

public abstract class Product {

    private String name;
    private double price;
    private int inventory;

    public Product(String name, double price, int inventory){
        this.name=name;
        this.price=price;
        this.inventory=inventory;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }
}
