package ir.ac.kntu.models;

import ir.ac.kntu.enums.GPUModel;
import ir.ac.kntu.enums.WebcamStatus;

public class Laptop extends Digital{

    private GPUModel GPUModel;
    private boolean btSupport;
    private WebcamStatus webcamStatus;


    public Laptop(String name, double price, Seller seller, int inventory, String brand,
                  double internalStorage, String ram, GPUModel GPUModel,
                  boolean btSupport, WebcamStatus webcamStatus) {
        super(name, price, seller, inventory, brand, internalStorage, ram);
        this.GPUModel = GPUModel;
        this.btSupport = btSupport;
        this.webcamStatus = webcamStatus;
    }

    // Optional: add getters/setters if needed
    public GPUModel hasGPUModel() {
        return GPUModel;
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

    public void setGPUModel(GPUModel GPUModel) {
        this.GPUModel = GPUModel;
    }

    public void setWebcamStatus(WebcamStatus webcamStatus) {
        this.webcamStatus = webcamStatus;
    }
}
