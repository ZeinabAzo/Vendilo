package ir.ac.kntu.models;

import ir.ac.kntu.enums.MobileNetworkType;
import ir.ac.kntu.enums.CameraResolution;

public class Mobile extends Digital {

    private CameraResolution frontCam;
    private CameraResolution backCame;
    private MobileNetworkType mobileNetType;

    public Mobile(String name, double price, int inventory, Seller seller,String type1,  String brand,
                  double internalStorage, String ram, CameraResolution frontCam,
                  CameraResolution backCam, MobileNetworkType type) {
        super(name, price, seller, "mobile", inventory, brand, internalStorage, ram);
        this.frontCam = frontCam;
        this.backCame = backCam;
        this.mobileNetType = type;
    }

    public CameraResolution getFrontCameraResolution() {
        return frontCam;
    }

    public CameraResolution getBackCameraResolution() {
        return backCame;
    }

    public MobileNetworkType getMobileNetType() {
        return mobileNetType;
    }

    public void setBackCameraResolution(CameraResolution cameraResolution) {
        this.backCame = cameraResolution;
    }

    public void setFrontCameraResolution(CameraResolution cameraResolution) {
        this.frontCam = cameraResolution;
    }

    public void setMobileNetType(MobileNetworkType mobileNetType) {
        this.mobileNetType = mobileNetType;
    }

    public String mobileToString() {
        return String.format("Mobile name: %s | price: %.2f | inventory: %d | seller: %s | front cam: %s | " +
                        "back cam: %s | network: %s",
               getName(),getPrice(), getInventory(), getSellerId(), frontCam, backCame, mobileNetType);
    }


}
