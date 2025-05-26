package ir.ac.kntu.models;

import ir.ac.kntu.enums.MobileNetworkType;
import ir.ac.kntu.enums.CameraResolution;

public class Mobile extends Digital {

    private CameraResolution frontCam;
    private CameraResolution backCame;
    private MobileNetworkType mobileNetType;

    public Mobile(String name, double price, int inventory, Seller seller, String brand,
                  double internalStorage, String ram, CameraResolution frontCam,
                  CameraResolution backCam, MobileNetworkType type) {
        super(name, price, seller, inventory, brand, internalStorage, ram);
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

}
