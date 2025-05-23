package ir.ac.kntu.models;

import ir.ac.kntu.enums.MobileNetworkType;
import ir.ac.kntu.enums.CameraResolution;

public class Mobile extends Digital{

    private CameraResolution frontCameraResolution;
    private CameraResolution backCameraResolution;
    private MobileNetworkType mobileNetworkType;

    public Mobile(String name,double price, int inventory, Seller seller,String brand,
                  double internalStorage, String ram, CameraResolution frontCameraResolution,
                  CameraResolution backCameraResolution, MobileNetworkType mobileNetworkType){
        super(name, price,seller, inventory, brand, internalStorage, ram);
        this.frontCameraResolution=frontCameraResolution;
        this.backCameraResolution=backCameraResolution;
        this.mobileNetworkType=mobileNetworkType;
    }

    public CameraResolution getFrontCameraResolution() {
        return frontCameraResolution;
    }

    public CameraResolution getBackCameraResolution() {
        return backCameraResolution;
    }

    public MobileNetworkType getMobileNetworkType() {
        return mobileNetworkType;
    }

    public void setBackCameraResolution(CameraResolution backCameraResolution) {
        this.backCameraResolution = backCameraResolution;
    }

    public void setFrontCameraResolution(CameraResolution frontCameraResolution) {
        this.frontCameraResolution = frontCameraResolution;
    }

    public void setMobileNetworkType(MobileNetworkType mobileNetworkType) {
        this.mobileNetworkType = mobileNetworkType;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
