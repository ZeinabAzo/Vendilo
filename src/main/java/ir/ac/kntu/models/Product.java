package ir.ac.kntu.models;

import ir.ac.kntu.util.PrintHelper;

public class Product implements Comparable<Product>{

    private String name;
    private double price;
    private int inventory;
    private String seller;
    private String type;
    private Integer rate;

    public Product() {
    }

    public Product(String name, double price, int inventory, Seller seller, String type) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.seller = seller.getShopID();
        this.type = type;
        rate = null;
    }

    public boolean rateProduct(int rate) {
        if (rate >= 0 && rate <= 5 && this.rate == null) {
            this.rate = rate;
            return true;
        } else {
            PrintHelper.printError("Invalid rate range, please try again.");
            return false;
        }
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
        if (inventory >= 0) {
            this.inventory = inventory;
            return true;
        }
        return false;
    }

    public boolean sellProduct() {
        if (inventory > 0) {
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

    public String getSellerId() {
        return seller;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
}
