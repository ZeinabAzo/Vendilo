package ir.ac.kntu.services;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.util.PrintHelper;

import java.util.HashMap;
import java.util.Map;

public class SearchProducts {

    private ProductDB productDB;
    private SellerDB sellerDB;

    public SearchProducts(ProductDB productDB, SellerDB sellerDB) {
        this.productDB = productDB;
        this.sellerDB = sellerDB;
    }

    public Map<Seller, Product> searchProductByName(String name) {
        HashMap<Seller, Product> filteredProducts = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                filteredProducts.put(sellerDB.findSeller(product.getSellerId()), product);
            }
        }

        return filteredProducts;
    }

    public Map<Seller, Product> searchByType(String typeName) {
        HashMap<Seller, Product> filteredProducts = new HashMap<>();
        Class<?> matchedClass = findProductType(typeName);

        if (matchedClass == null) {
            PrintHelper.printError("No such product type: " + typeName);
            return filteredProducts;
        }

        for (Product product : productDB.getProducts()) {
            if (matchedClass.isInstance(product)) {
                filteredProducts.put(sellerDB.findSeller(product.getSellerId()), product);
            }
        }

        return filteredProducts;
    }

    public Map<Seller, Product> allFilteredSearch(double[] priceRange, String name, String typeName) {
        HashMap<Seller, Product> filtered = new HashMap<>();
        Class<?> matchedClass = findProductType(typeName);

        if (matchedClass == null) {
            PrintHelper.printError("No such product type: " + typeName);
            return filtered;
        }

        for (Product product : productDB.getProducts()) {
            boolean matchesType = matchedClass.isInstance(product);
            boolean matchesName = product.getName().equalsIgnoreCase(name);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesType && matchesName && matchesPrice) {
                filtered.put(sellerDB.findSeller(product.getSellerId()), product);
            }
        }

        return filtered;
    }

    public Map<Seller, Product> searchByTypeAndPrice(String typeName, double[] priceRange) {
        HashMap<Seller, Product> filtered = new HashMap<>();
        Class<?> matchedClass = findProductType(typeName);

        if (matchedClass == null) {
            PrintHelper.printError("No such product type: " + typeName);
            return filtered;
        }

        for (Product product : productDB.getProducts()) {
            boolean matchesType = matchedClass.isInstance(product);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesType && matchesPrice) {
                filtered.put(sellerDB.findSeller(product.getSellerId()), product);
            }
        }

        return filtered;
    }

    public Map<Seller, Product> searchByNameAndPrice(String name, double[] priceRange) {
        HashMap<Seller, Product> filtered = new HashMap<>();

        for (Product product : productDB.getProducts()) {
            boolean matchesName = product.getName().equalsIgnoreCase(name);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesName && matchesPrice) {
                filtered.put(sellerDB.findSeller(product.getSellerId()), product);
            }
        }

        return filtered;
    }

    private Class<?> findProductType(String userInput) {
        if (userInput == null) {
            return null;
        }

        String target = userInput.trim().toLowerCase();

        for (Product product : productDB.getProducts()) {
            Class<?> productClass = product.getClass();
            String simpleName = productClass.getSimpleName().toLowerCase();

            if (simpleName.equals(target)) {
                return productClass;
            }
        }

        return null;
    }
}
