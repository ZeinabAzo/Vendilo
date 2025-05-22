package ir.ac.kntu.models;

public class Digital extends Product{

    private String brand;
    private double internalStorage;
    private String ram;

    public Digital(String name,double price, Seller seller, int inventory, String brand,
                   double internalStorage, String ram){
        super(name, price, inventory, seller);
        this.brand=brand;
        this.internalStorage=internalStorage;
        this.ram=ram;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(double internalStorage) {
        this.internalStorage = internalStorage;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
}
