package ir.ac.kntu.ui.sellermenu;

import ir.ac.kntu.controllers.SellControl;
import ir.ac.kntu.enums.*;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.InputHelper;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class SellProdMenu {

    private final SellControl sellerController;

    public SellProdMenu(SellControl sellerController) {
        this.sellerController = sellerController;
    }

    public void productOptions() {
        while (true) {
            PrintHelper.upperBorder(" Product Management Menu");
            PrintHelper.option(1, "Show All Products");
            PrintHelper.option(2, "Add New Product");
            PrintHelper.option(3, "Return to Previous Menu");
            PrintHelper.lowerBorder(" Product Management Menu");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showProducts();
                case 2 -> addProduct();
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid choice. Please select a valid option.");
            }
        }
    }


    public void showProducts() {
        while (true) {
            Product product = sellerController.showProducts();
            if (product == null) {
                return;
            }

            int choice = getChoice();

            switch (choice){
                case 1, 2 -> extracted(choice, product);
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void extracted(int choice, Product product) {
        PrintHelper.ask("Enter the number of items to " + (choice == 1 ? "add" : "remove") + ":");
        int count = ScannerWrapper.nextInt();
        if (choice == 2) {
            count = -count;
        }

        boolean success = sellerController.changeInventory(count, product);
        if (success) {
            PrintHelper.printSuccess("Inventory updated successfully.");
        } else {
            PrintHelper.printError("Operation failed. Please try again.");
        }
    }

    private static int getChoice() {
        PrintHelper.ask("What would you like to do with this product?");
        PrintHelper.option(1, "Add Item to Inventory");
        PrintHelper.option(2, "Remove Item from Inventory");
        PrintHelper.option(3, "Return to Previous Menu");
        return ScannerWrapper.nextInt();
    }

    public void addProduct() {
        while (true) {
            PrintHelper.ask("What type of product do you want to add?");
            PrintHelper.option(1, "Mobile");
            PrintHelper.option(2, "Laptop");
            PrintHelper.option(3, "Book");
            PrintHelper.option(4, "return");

            int choice = ScannerWrapper.nextInt();
            Product product;

            switch (choice) {
                case 1 -> product = createMobile(sellerController.getSeller());
                case 2 -> product = createLaptop(sellerController.getSeller());
                case 3 -> product = createBook(sellerController.getSeller());
                case 4 -> {
                    return;
                }
                default -> {
                    PrintHelper.printError("Invalid choice. Please select a valid option.");
                    continue;
                }
            }

            if (product == null) {
                PrintHelper.printError("Product creation cancelled or invalid input.");
                continue;
            }
            sellerController.addProduct(product);
            PrintHelper.printSuccess("New product was added successfully ✓ ");
        }
    }

    public static Mobile createMobile(Seller seller) {
        String name = InputHelper.inputString("Enter mobile name:");
        double price = InputHelper.inputDouble("Enter price:");
        int inventory = InputHelper.inputInt("Enter inventory count:");
        String brand = InputHelper.inputString("Enter brand:");
        double internalStorage = InputHelper.inputDouble("Enter internal storage (GB):");
        String ram = InputHelper.inputString("Enter RAM (e.g., 4GB):");

        CameraResolution frontCam = getCameraResolution();
        if (frontCam == null) {
            return null;
        }

        CameraResolution backCam = getResolution();
        if (backCam == null) {
            return null;
        }

        MobileNetworkType networkType = getMobileNetworkType();
        if (networkType == null) {
            return null;
        }

        return new Mobile(name, price, inventory, seller,brand, internalStorage, ram, frontCam, backCam, networkType);
    }

    private static MobileNetworkType getMobileNetworkType() {
        MobileNetworkType networkType = null;
        while (networkType == null) {
            networkType = InputHelper.inputEnum("Choose mobile network type:", MobileNetworkType.class);
            if (networkType == null) {
                PrintHelper.printError("Invalid mobile network type. Please try again.");
                return null;
            }
        }
        return networkType;
    }

    private static CameraResolution getResolution() {
        CameraResolution backCam = null;
        while (backCam == null) {
            backCam = InputHelper.inputEnum("Choose back camera resolution:", CameraResolution.class);
            if (backCam == null) {
                PrintHelper.printError("Invalid back camera resolution. Please try again.");
                return null;
            }
        }
        return backCam;
    }

    private static CameraResolution getCameraResolution() {
        CameraResolution frontCam = null;
        while (frontCam == null) {
            frontCam = InputHelper.inputEnum("Choose front camera resolution:", CameraResolution.class);
            if (frontCam == null) {
                PrintHelper.printError("Invalid front camera resolution. Please try again.");
                return null;
            }
        }
        return frontCam;
    }


    public static Book createBook(Seller seller) {
        String name = InputHelper.inputString("Enter book title:");
        double price = InputHelper.inputDouble("Enter price:");
        int inventory = InputHelper.inputInt("Enter inventory count:");
        String authorName = InputHelper.inputString("Enter author name:");
        double pageCount = InputHelper.inputDouble("Enter page count:");

        BookGenre bookGenre = null;
        while (bookGenre == null) {
            bookGenre = InputHelper.inputEnum("Choose book genre:", BookGenre.class);
            if (bookGenre == null) {
                PrintHelper.printError("Invalid book genre. Please try again.");
                return null;
            }
        }

        AgeGroup ageGroup = null;
        while (ageGroup == null) {
            ageGroup = InputHelper.inputEnum("Choose target age group:", AgeGroup.class);
            if (ageGroup == null) {
                PrintHelper.printError("Invalid age group. Please try again.");
                return null;
            }
        }

        String isbn = InputHelper.inputString("Enter isbn:");

        return new Book(name, price, inventory, seller, authorName, pageCount, bookGenre, ageGroup, isbn);
    }


    public static Laptop createLaptop(Seller seller) {
        String name = InputHelper.inputString("Enter product name:");
        double price = InputHelper.inputDouble("Enter price:");
        int inventory = InputHelper.inputInt("Enter inventory count:");
        String brand = InputHelper.inputString("Enter brand:");
        double internalStorage = InputHelper.inputDouble("Enter internal storage (GB):");
        String ram = InputHelper.inputString("Enter RAM:");

        GPUModel gpuModel = null;
        while (gpuModel == null) {
            gpuModel = InputHelper.inputEnum("Choose GPU model:", GPUModel.class);
            if (gpuModel == null) {
                PrintHelper.printError("Invalid GPU model. Please try again.");
                return null;
            }
        }

        boolean btSupport = InputHelper.inputYesNo("Does it support Bluetooth?");

        WebcamStatus webcamStatus = null;
        while (webcamStatus == null) {
            webcamStatus = InputHelper.inputEnum("Choose webcam status:", WebcamStatus.class);
            if (webcamStatus == null) {
                PrintHelper.printError("Invalid webcam status. Please try again.");
                return null;
            }
        }

        return new Laptop(name, price, seller, inventory, brand, internalStorage, ram, gpuModel, btSupport, webcamStatus);
    }


}
