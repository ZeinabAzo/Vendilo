package ir.ac.kntu.models;

import ir.ac.kntu.enums.WebcamStatus;

public class Laptop extends Digital{

    private boolean GPUModel;
    private boolean btSupport;
    private WebcamStatus webcamStatus;


    public Laptop(String name, double price, int inventory, String brand,
                  double internalStorage, String ram, boolean GPUModel,
                  boolean btSupport, WebcamStatus webcamStatus) {
        super(name, price, inventory, brand, internalStorage, ram);
        this.GPUModel = GPUModel;
        this.btSupport = btSupport;
        this.webcamStatus = webcamStatus;
    }

    // Optional: add getters/setters if needed
    public boolean hasGPUModel() {
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

    public void setGPUModel(boolean GPUModel) {
        this.GPUModel = GPUModel;
    }

    public void setWebcamStatus(WebcamStatus webcamStatus) {
        this.webcamStatus = webcamStatus;
    }
}
