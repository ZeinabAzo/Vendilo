package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.ui.ShowProductInfo;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.SplitDisplay;

import java.util.ArrayList;
import java.util.List;

import static ir.ac.kntu.Vendilo.addProducts;

public class SellControl {

    private AdminDB adminDB;
    private ProductDB productDB;
    private Seller seller;

    public SellControl( ProductDB productDB, AdminDB adminDB, Seller seller) {
        this.seller = seller;
        this.productDB = productDB;
        this.adminDB=adminDB;
    }

    public void setServices() {//add necessary services
    }



    public void sendRequest() {
        adminDB.getAuthRequest().add(new AuthRequest(seller, null));
    }


    public boolean response() {
        return adminDB.getAuthRequest(seller).isAccepted();
    }

    public String getResponse(){
        return adminDB.getAuthRequest(seller).getResponse();
    }

    public Seller getSeller() {
        return seller;
    }

    public Product showProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for(Product p : seller.getProductsForSale()){
            if(p instanceof Mobile){
                products.add((Mobile) p);
            }else if(p instanceof Book){
                products.add((Book) p);
            }else if(p instanceof Laptop){
                products.add((Laptop) p);
            }
        }
          int chosen = SplitDisplay.show(products);
        if (chosen < -1 || chosen > products.size()) {
            PrintHelper.printError("Invalid choice!");
            return null;
        } else if (chosen == -1) {
            return null;
        };
        if (products.get(chosen) instanceof Mobile) {
            ShowProductInfo.showMobile((Mobile) products.get(chosen));
        } else if (products.get(chosen) instanceof Laptop) {
            ShowProductInfo.showLaptop((Laptop) products.get(chosen));
        } else if (products.get(chosen) instanceof Book) {
            ShowProductInfo.showBook((Book) products.get(chosen));
        }
        return products.get(chosen);
    }

    public boolean changeInventory(int count, Product product) {
        return product.setInventory(product.getInventory() + count);
    }

    public void addProduct(Product newProduct) {

        if(newProduct instanceof Mobile){
            seller.addMobile((Mobile) newProduct);
        }else if(newProduct instanceof Book){
            seller.addBook((Book) newProduct);
        }else if(newProduct instanceof Laptop){
            seller.addLaptop((Laptop) newProduct);
        }
    }

    public void withdraw(double value) {
        seller.getWallet().withdraw(value);
    }

    public void showOrders() {
        SplitDisplay.show(seller.getOrders());
    }
}
