package ir.ac.kntu.models;

import ir.ac.kntu.enums.DiscountType;
import ir.ac.kntu.util.CodeGenerator;
import ir.ac.kntu.util.PrintHelper;

public class Discount {// TODO: be careful and customize your getters and setters
    private String disCode;
    private DiscountType type;
    private double value;
    private int maxUsages;

    public Discount(DiscountType type, double value, int maxUsages) {
        setDisCode();
        this.type = type;
        this.value = value;
        this.maxUsages = maxUsages;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisCode() {
        disCode = CodeGenerator.generateUniqueCode();
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getMaxUsages() {
        return maxUsages;
    }

    public void setMaxUsages(int maxUsages) {
        this.maxUsages = maxUsages;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "maxUsages=" + maxUsages +
                ", value=" + value +
                '}';
    }

    public String showALL() {
        return PrintHelper.ConsoleColors.DIM + PrintHelper.ConsoleColors.GREEN +
                " -------------------$  DISCOUNT  $--------------------- \n" +
                "  ► max usage:" + maxUsages + "\n" +
                "  ► value: " + value + "\n" +
                "  ► type: " + type.toString() + "\n" +
                "  ► code: " + disCode + "\n" +
                "-------------------------------------------------------"
                + PrintHelper.ConsoleColors.RESET;
    }
}
