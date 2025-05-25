package ir.ac.kntu.services;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchProducts {

    private ProductDB productDB;

    public SearchProducts(ProductDB productDB){
        this.productDB = productDB;
    }

    public HashMap<Seller, Product> searchProductByName(String name){
        HashMap<Seller, Product> filteredProducts = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            if (product.getName().equals(name)) {
                filteredProducts.put(product.getSeller(), product);
            }
        }

        return filteredProducts;
    }

    public HashMap<Seller, Product> searchByType(Class<?> type) {
        HashMap<Seller, Product> filteredProducts = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            if (type.isInstance(product)) {
                filteredProducts.put(product.getSeller(), product);
            }
        }

        return filteredProducts;
    }

    public HashMap<Seller, Product> allFilteredSearch(double[] priceRange, String name, Class<?> type) {
        HashMap<Seller, Product> filtered = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            boolean matchesType = type.isInstance(product);
            boolean matchesName = product.getName().equals(name);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesType && matchesName && matchesPrice) {
                filtered.put(product.getSeller(), product);
            }
        }

        return filtered;
    }

    public HashMap<Seller, Product> searchByTypeAndPrice(Class<?> type, double[] priceRange) {
        HashMap<Seller, Product> filtered = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            boolean matchesType = type.isInstance(product);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesType && matchesPrice) {
                filtered.put(product.getSeller(), product);
            }
        }

        return filtered;
    }

    public HashMap<Seller, Product> searchByNameAndPrice(String name, double[] priceRange) {
        HashMap<Seller, Product> filtered = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            boolean matchesName = product.getName().equals(name);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesName && matchesPrice) {
                filtered.put(product.getSeller(), product);
            }
        }

        return filtered;
    }
}
