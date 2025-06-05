package ir.ac.kntu.models;

import ir.ac.kntu.util.PrintHelper;

import java.util.HashSet;
import java.util.Set;

public class Product implements Comparable<Product> {

    private String name;
    private double price;
    private int inventory;
    private String seller;
    private String type;
    private int rate;
    private Set<String> ratedUserIds; // You can initialize this in the constructor

    public Product() {
    }

    public Product(String name, double price, int inventory, Seller seller, String type) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.seller = seller.getShopID();
        this.type = type;
        rate = -1;
        ratedUserIds = new HashSet<>();
    }

    public Set<String> getRatedUserIds() {
        if (ratedUserIds == null) {
            ratedUserIds = new HashSet<>();
        }
        return ratedUserIds;
    }

    public boolean rateProduct(String userId, int rate) {
        if (rate < 0 || rate > 5) {
            PrintHelper.printError("Invalid rate range, please try again.");
            return false;
        }

        if (ratedUserIds == null) {
            ratedUserIds = new HashSet<>();
        }

        if (ratedUserIds.contains(userId)) {
            PrintHelper.printError("You have already rated this product.");
            return false;
        }

        if (this.rate == -1 || ratedUserIds.isEmpty()) {
            this.rate = rate;
        } else {
            this.rate = (this.rate * ratedUserIds.size() + rate) / (ratedUserIds.size() + 1);
        }

        ratedUserIds.add(userId);
        PrintHelper.printSuccess("Rated successfully. New average: " + this.rate);
        return true;
    }



    public int getRateCount() {
        return ratedUserIds.size();
    }

    public Integer getRate() {
        return rate;
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", type='" + type + '\'' +
                ", rate=" + rate +
                '}';
    }

    public void setRatedUserIds(Set<String> ratedUserIds) {
        this.ratedUserIds = ratedUserIds;
    }
}
