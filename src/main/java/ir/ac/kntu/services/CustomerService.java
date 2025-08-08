package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.enums.DiscountType;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.authentication.AuthService;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

import java.time.LocalDate;

import static ir.ac.kntu.util.PrintHelper.printError;

public class CustomerService {

    private SellerDB sellerDB;
    private double shippingFee = 50;
    private CustomerDB customerDB;


    public CustomerService(CustomerDB customerDB, SellerDB sellerDB) {
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
    }

    public void purchaseCart(Cart cart, Customer customer, Address address, Discount discount) {
        if (cart == null || customer == null) {
            printError("Cart or customer not recognized.");
            return;
        }

        cart.setShippingAddress(address);
        double totalPrice = getPrice(customer, cart, discount);
        boolean success = customer.getWallet().withdraw(totalPrice);

        if (!success) {
            printError("Insufficient balance in wallet.");
            return;
        }

        Cart customerCart = customer.getCart(cart);
        if (customerCart == null) {
            printError("The cart was not found in customer's profile.");
            return;
        }

        for (Order order : cart.getOrders()) {
            Seller seller = sellerDB.findSeller(order.getShopID());
            double amount = seller.getWallet().receivePaymentFromSale(order.getProduct().getPrice());
            order.getProduct().sellProduct();//new - forgot to do this =( T_T
            Transaction transaction = new Transaction(seller.getShopID(), amount, LocalDate.now());
            seller.getWallet().addTransaction(transaction);
        }

        customerCart.setPurchased(true);
        customerCart.setAmountPurchased(totalPrice);
        customerCart.setDatePurchased(LocalDate.now());
    }

    private double getPrice(Customer customer, Cart cart, Discount discount) {
        double totalPrice = getTotalPrice(cart, customer);
        if(discount != null && discount.getMaxUsages()>0){
            if(discount.getType() == DiscountType.GENERAL){
                totalPrice = totalPrice * ((100-discount.getValue())/100);
                discount.setMaxUsages(discount.getMaxUsages()-1);
            }else if (discount.getType() == DiscountType.SPECIAL){
                if(totalPrice>10*discount.getValue()){
                    totalPrice -= discount.getValue();
                    discount.setMaxUsages(discount.getMaxUsages() - 1);
                }else{
                    printError("your purchase wasn't enough to use your discount");
                }
            }else{
                printError("didn't use discount: CustomerService -> purchaseCart");
            }
        }
        return totalPrice;
    }


    private double getTotalPrice(Cart cart, Customer customer) {
        boolean shippingCost = true;
        double totalPrice = 0;

        for (Order order : cart.getOrders()) {
            if (order != null && order.getProduct() != null) {
                Seller seller = sellerDB.findSeller(order.getShopID());
                if (!(seller.getShopLocation().getState().equals(cart.getShippingAddress().getState()))) {
                    shippingCost = false;
                }
                totalPrice += order.getProduct().getPrice();
            }
        }

        if(customer.hasVendiloPlus()){
            totalPrice = totalPrice*95/100;
        }

        if (shippingCost) {
            if(!customer.hasVendiloPlus()){
                totalPrice += shippingFee / 3;
            }
        } else {
            if(customer.hasVendiloPlus()){
                totalPrice += shippingFee / 3;
            }else {
                totalPrice += shippingFee;
            }
        }
        return totalPrice;
    }

    public void setEmail(Customer customer, String email) {
        if (isCorrectEmail(email)) {
            customer.setEmail(email);
        }
    }

    private boolean isCorrectEmail(String email) {
        if (AuthService.isValidEmail(email)) {
            if (hasDupEmail(email)) {
                printError("Such an evil, using someone else's email!!");
                return false;
            }
            return true;
        } else {
            printError("Invalid email");
            return false;
        }
    }

    private boolean hasDupEmail(String email) {
        String foundEmail = customerDB.getCustomers().stream()
                .map(Customer::getEmail)
                .filter(email::equals)
                .findFirst()
                .orElse(null);
        return foundEmail != null;
    }

    public void setPassword(Customer customer, String password) {
        if (isCorrectPassword(password)) {
            customer.setPassword(password);
        }
    }

    private boolean isCorrectPassword(String password) {
        if (AuthService.isValidPassword(password)) {
            if (hasDupPassword(password)) {
                printError("Password already in use by another user! Be original!");
                return false;
            }
            return true;
        } else {
            printError("Invalid password");
            return false;
        }
    }

    private boolean hasDupPassword(String password) {
        String foundPassword = customerDB.getCustomers().stream()
                .map(Customer::getPassword)
                .filter(password::equals)
                .findFirst()
                .orElse(null);
        return foundPassword != null;
    }

    public void deleteUnavailable(Cart cart) {
        cart.getOrders().removeIf(o -> o.getProduct().getInventory() <= 0);
    }


    public void rateProduct(Customer customer, Order order) {

        boolean success = false;
        while (!success) {
            PrintHelper.ask("Please rate this product-be careful bro you only get to do it once!(-1 to return");
            double rate = ScannerWrapper.nextDouble();
            if (rate == -1) {
                return;
            }
            success = order.getProduct().rateProduct(customer.getEmail(), rate);
        }
    }

    public void setPhoneNum(Customer customer, String num) {
        if (isCorrectPhone(num)) {
            customer.setPhoneNumber(num);
        }
    }

    private boolean isCorrectPhone(String num) {
        if (AuthService.isValidPhoneNumber(num)) {
            if (hasDupPhone(num)) {
                printError("Phone number already in use by another user! Be original!");
                return false;
            }
            return true;
        } else {
            printError("Invalid number");
            return false;
        }
    }

    private boolean hasDupPhone(String num) {
        String foundPNum = customerDB.getCustomers().stream()
                .map(Customer::getPhoneNumber)
                .filter(num::equals)
                .findFirst()
                .orElse(null);
        return foundPNum != null;
    }

    public void deleteAccount(Customer customer) {
        customerDB.getCustomers().remove(customer);
    }

    public Discount getDiscount(String code, Customer customer) {
        return customerDB.getCustomer(customer).getDiscounts().stream().
                filter(discount -> discount.getDisCode().equals(code)).findFirst().orElse(null);
    }
}