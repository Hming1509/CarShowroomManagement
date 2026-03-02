/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BrandList;

/**
 *
 * @author asus
 */
public class Brand {
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    public Brand() {
        this.brandID = null;
        this.brandName = null;
        this.soundBrand = null;
        this.price = 0;
    }

    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-40s | %-20s | %.3fB", brandID, brandName, soundBrand, price);
    }

    public String toStringToSave() {
        return this.brandID + "," + this.brandName + "," + this.soundBrand + ": " + this.price + "B";
    }
}
