package ir.ac.kntu.models;

import ir.ac.kntu.enums.GPUModel;
import ir.ac.kntu.enums.WebcamStatus;

public class Laptop extends Digital {

    private GPUModel gpuModel;
    private boolean btSupport;
    private WebcamStatus webcamStatus;

    public Laptop(String name, double price, Seller seller, int inventory, String brand,
                  double internalStorage, String ram, GPUModel gpuModel,
                  boolean btSupport, WebcamStatus webcamStatus) {
        super(name, price, seller, "laptop",inventory, brand, internalStorage, ram);
        this.gpuModel = gpuModel;
        this.btSupport = btSupport;
        this.webcamStatus = webcamStatus;
    }


    // Optional: add getters/setters if needed

    public GPUModel getGpuModel() {
        return gpuModel;
    }

    public boolean hasBtSupport() {
        return btSupport;
    }

    public WebcamStatus getWebcamStatus() {
        return webcamStatus;
    }

    public boolean isBtSupport() {
        return btSupport;
    }

    public void setGpuModel(GPUModel gpuModel) {
        this.gpuModel = gpuModel;
    }

    public void setWebcamStatus(WebcamStatus webcamStatus) {
        this.webcamStatus = webcamStatus;
    }

    public String lapToString() {
        return String.format("Laptop name: %s | price: %.2f | inventory: %d | seller: %s | GPU: %s | BT: %s | " +
                "Webcam: %s | rate : %s ", getName(),getPrice(), getInventory(), getSellerId(), gpuModel, btSupport ? "Yes" : "No",
                webcamStatus, this.getRate());
    }

}
