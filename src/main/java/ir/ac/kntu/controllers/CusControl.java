package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.AuthService;
import ir.ac.kntu.services.CustomerService;
import ir.ac.kntu.services.SearchProducts;

import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.time.LocalDate;
import java.util.List;

public class CusControl {

    private ProductDB productDB;
    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private Customer customer;
    private AdminDB adminDB;
    private SearchProducts searchProducts;
    private CustomerService customerServ;

    public CusControl(Customer customer, CustomerDB customerDB, SellerDB sellerDB, AdminDB adminDB, ProductDB productDB) {
        this.customer = customer;
        this.productDB = productDB;
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
        this.adminDB = adminDB;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setServices() {//add necessary services
        this.searchProducts = new SearchProducts(productDB);
        this.customerServ = new CustomerService(customerDB, sellerDB);
    }

    public List<Product> searchByName(String name) {
        return searchProducts.searchProductByName(name);
    }

    public List<Product> searchByNameAndPrice(String name, double[] priceRange) {
        return searchProducts.searchByNameAndPrice(name, priceRange);
    }

    public List<Product> searchByTypeAndPrice(String type, double[] priceRange) {
        return searchProducts.searchByTypeAndPrice(type, priceRange);

    }

    public List<Product> searchByAllFilters(String type, String name, double[] priceRange) {
        return searchProducts.allFilteredSearch(priceRange, name, type);

    }

    public void orderProduct(Product product) {
        Cart cart = findCart();
        Seller seller = sellerDB.findSeller(product.getSellerId());
        String shopID = seller.getShopID();
        Order order = new Order(product, shopID, LocalDate.now());
        cart.addToCart(order);
        seller.addOrder(order);
    }

    private Cart findCart() {// this doesn't work
        int chosen = SplitDisplay.show(customer.getCarts());
        if (chosen == -1) {
            PrintHelper.printInfo("Darling you've got no cart, let's create one:");
            Cart cart = new Cart();
            customer.setCarts(cart);
            PrintHelper.printSuccess("We have created a new cart just for you. ♡❤ ");
            return cart;
        }
        return customer.getCart(chosen);
    }

    public void deleteCart(Cart cart) {
        Cart cart1 = customer.getCart(cart);
        if (cart1 != null) {
            customer.getCarts().remove(cart1);
        } else {
            PrintHelper.printError("cart is null: cusControl-deleteCart");
        }
    }

    public List<Product> searchByType(String type) {
        return searchProducts.searchByType(type);
    }

    public void purchaseCart(Address address, Cart cart) {
        if (!customer.getCart(cart).isPurchased()) {
            if (isAvailable(cart)) {
                customerServ.purchaseCart(cart, customer, address);
            } else {
                handleUnav(cart);
            }
        } else {
            PrintHelper.printError("This cart is already purchased, hooray! ᓚᘏᗢ ");
        }
    }

    private void handleUnav(Cart cart) {
        PrintHelper.ask("What will you do now?");
        PrintHelper.option(1, "forget it, let's return!");
        PrintHelper.option(2, "delete all unavailable orders in the cart");
        PrintHelper.option(3, "exit");
        int choice = ScannerWrapper.nextInt();

        switch (choice) {
            case 1 -> {
            }
            case 2 -> deleteUnavailable(cart);
            case 3 -> Exit.exit();
            default -> PrintHelper.printError("Invalid command");
        }
    }

    private void deleteUnavailable(Cart cart) {
        customerServ.deleteUnavailable(cart);
    }

    private boolean isAvailable(Cart cart) {
        boolean available = true;
        for (Order o : cart.getOrders()) {
            if (o.getProduct().getInventory() <= 0) {
                available = false;
                break;
            }
        }
        return available;
    }

    public void deleteAddress(int index) {
        customer.getAddresses().remove(customer.getAddress(index));
    }

    public void editDescription(int index) {
        PrintHelper.ask("Enter new description: ");
        String des = ScannerWrapper.nextLine();
        customer.getAddress(index).setDescription(des);
    }

    public void editCity(int index) {
        PrintHelper.ask("Enter new city: ");
        String city = ScannerWrapper.nextLine();
        customer.getAddress(index).setCity(city);
    }

    public void editState(int index) {
        PrintHelper.ask("Enter new state: ");
        String state = ScannerWrapper.nextLine();
        customer.getAddress(index).setState(state);
    }

    public void editTitle(int index) {
        PrintHelper.ask("Enter new title: ");
        String title = ScannerWrapper.nextLine();
        customer.getAddress(index).setTitle(title);
    }

    public void showTransactions() {
        if(customer.getWallet().getTransactions().isEmpty()){
            PrintHelper.printError("No transactions");
        }else {
            SplitDisplay.show(customer.getWallet().getTransactions());
        }
    }

    public List<Transaction> getFiltered(LocalDate pDate, LocalDate sDate) {
        return customer.getWallet().getTransactions().stream().filter(t -> t.getDate().isBefore(sDate) &&
                t.getDate().isAfter(pDate)).toList();
    }

    public boolean chargeBalance(double amount) {
        return customer.getWallet().deposit(amount);
    }

    public void showPurchCart() {
        List<Cart> purchased = customer.getCarts().stream().filter(Cart::isPurchased).toList();
        int choice = SplitDisplay.show(purchased);
        if (choice > 0 && choice < purchased.size()) {
            int chosen = SplitDisplay.show(purchased.get(choice).getOrders());
            customerServ.rateProduct(purchased.get(choice).getOrders().get(chosen));
        }
    }

    public void editfName() {
        PrintHelper.ask("Enter new name: ");
        String name = ScannerWrapper.nextLine().trim();
        if(name.matches("\\w+")){
            customer.setfName(name);
            PrintHelper.printSuccess("Data was updated successfully.");
        }else{
            PrintHelper.printError("Invalid name input, couldn't edit.");
        }
    }

    public void editlName() {
        PrintHelper.ask("Enter new family name: ");
        String name = ScannerWrapper.nextLine().trim();
        if(name.matches("\\w+")){
            customer.setlName(name);
            PrintHelper.printSuccess("Data was updated successfully.");
        }else{
            PrintHelper.printError("Invalid name input, couldn't edit.");
        }
    }

    public void editEmail() {
        PrintHelper.ask("Enter your new email: ");
        String email = ScannerWrapper.nextLine().trim();
        if(AuthService.isValidEmail(email)){
            customerServ.setEmail(customer, email);
            PrintHelper.printSuccess("Data was updated successfully.");
        }else{
            PrintHelper.printError("Invalid name input, couldn't edit.");
        }
    }

    public void editPassword() {
        PrintHelper.ask("Enter your new password: ");
        String pass = ScannerWrapper.nextLine().trim();
        if(AuthService.isValidPassword(pass)){
            customerServ.setPassword(customer, pass);
            PrintHelper.printSuccess("Data was updated successfully.");
        }else{
            PrintHelper.printError("Invalid name input, couldn't edit.");
        }
    }

    public void sendComplaint(String context) {
        Complaint complaint = new Complaint(context, customer.getEmail());
        adminDB.addCusComplaint(complaint);
    }

    public void editPhoneNum() {
        PrintHelper.ask("Enter your new phone number: ");
        String num = ScannerWrapper.nextLine().trim();
        if(AuthService.isValidPhoneNumber(num)){
            customerServ.setPhoneNum(customer, num);
            PrintHelper.printSuccess("Data was updated successfully.");
        }else{
            PrintHelper.printError("Invalid name input, couldn't edit.");
        }
    }

    public void addAddress(Address address) {
        customer.addAddress(address);
    }
}