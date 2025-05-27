package ir.ac.kntu.services;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.util.PrintHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchProducts {

    private ProductDB productDB;

    public SearchProducts(ProductDB productDB) {
        this.productDB = productDB;
    }

    public List<Product> searchProductByName(String name) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : productDB.getProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public List<Product> searchByType(String typeName) {
        List<Product> filteredProducts = new ArrayList<>();
        Class<?> matchedClass = findProductType(typeName);

        if (matchedClass == null) {
            PrintHelper.printError("No such product type: " + typeName);
            return filteredProducts;
        }

        for (Product product : productDB.getProducts()) {
            if (matchedClass.isInstance(product)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public List<Product> allFilteredSearch(double[] priceRange, String name, String typeName) {
        List<Product> filtered = new ArrayList<>();
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
                filtered.add(product);
            }
        }

        return filtered;
    }

    public List<Product> searchByTypeAndPrice(String typeName, double[] priceRange) {
        List<Product> filtered = new ArrayList<>();
        Class<?> matchedClass = findProductType(typeName);

        if (matchedClass == null) {
            PrintHelper.printError("No such product type: " + typeName);
            return filtered;
        }

        for (Product product : productDB.getProducts()) {
            boolean matchesType = matchedClass.isInstance(product);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesType && matchesPrice) {
                filtered.add(product);
            }
        }

        return filtered;
    }

    public List<Product> searchByNameAndPrice(String name, double[] priceRange) {
        List<Product> filtered = new ArrayList<>();

        for (Product product : productDB.getProducts()) {
            boolean matchesName = product.getName().equalsIgnoreCase(name);
            boolean matchesPrice = product.getPrice() >= priceRange[0] && product.getPrice() <= priceRange[1];

            if (matchesName && matchesPrice) {
                filtered.add(product);
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
