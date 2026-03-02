/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarList;

/**
 *
 * @author asus
 */
public class Car {
    private String carID;
    private String brandID;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
        this.carID = null;
        this.brandID = null;
        this.color = null;
        this.frameID = null;
        this.engineID = null;
    }

    public Car(String carID, String brandID, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brandID = brandID;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s",carID, brandID, color, frameID, engineID);
    }
}
