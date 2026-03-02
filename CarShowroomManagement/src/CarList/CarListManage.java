/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarList;

import BrandList.Brand;
import BrandList.BrandListManage;
import carshowroommanagement.Validation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author asus
 */
public class CarListManage {
    private static CarListManage list;
    private List<Car> carList;
    private ICarData carData;
    private String fileName = "cars.txt";
    private BrandListManage brandList; //Dependency: CarList need BrandList to active

    private CarListManage(BrandListManage brandList) {
        this.carData = new CarFile();
        this.carList = carData.loadCarList(fileName);
        this.brandList = brandList;
    }

    public static CarListManage getList(BrandListManage brandList) {
        if (list == null) {
            list = new CarListManage(brandList);
        }
        return list;
    }

    public Car getCarByID(String carID) {
        Car result = null;
        if (carID == null || carID.trim().isEmpty()) {
            result = null;
        } 
        for (Car car : carList) {
            if (car.getCarID().equalsIgnoreCase(carID)) {
                result = car;
            }
        }
        return result;
    }

    public boolean isFrameIDUnique(String frameID) {
        boolean check = true;
        for (Car car : carList) {
            if (car.getFrameID().equalsIgnoreCase(frameID)) {
                check = false; 
                break;
            }
        }
        return check; 
    }
    
    public boolean isEngineIDUnique(String engineID) {
        boolean check = true;
        for (Car car : carList) {
            if (car.getEngineID().equalsIgnoreCase(engineID)) {
                check = false; 
                break;
            }
        }
        return check; 
    }

    private void headerForCarList() {
        System.out.println("\n===================================== CAR LIST =====================================");
        System.out.printf(String.format("%-10s | %-30s | %-10s | %-10s | %-10s\n", "Car ID", "Brand name", "Color", "Frame ID", "Engine ID"));
        System.out.println(new String(new char[84]).replace("\0", "-"));
    }

    private void headerForSearchResult() {
        System.out.println("\n================================== SEARCH RESULTS ==================================");
        System.out.printf(String.format("%-10s | %-30s | %-10s | %-10s | %-10s\n", "Car ID", "Brand name", "Color", "Frame ID", "Engine ID"));
        System.out.println(new String(new char[84]).replace("\0", "-"));
    }

    private void printACarDetails(Car car) {
        Brand brand = brandList.getBrandByID(car.getBrandID());
        String brandName;
        if (brand != null) {
            brandName = brand.getBrandName();
        } else {
            brandName = "N/A";
        }
        System.out.printf(String.format("%-10s | %-30s | %-10s | %-10s | %-10s\n", car.getCarID(), brandName, car.getColor(), car.getFrameID(), car.getEngineID()));
    }

    public void listAllCarsSorted() {
        if (carList.isEmpty()) {
            System.out.println("Car list is empty!!!!");
        } else {
            List<Car> sortedList = new ArrayList<>(this.carList);
            Comparator<Car> carCompare = new Comparator<Car>() {
                @Override
                public int compare(Car c1, Car c2) {
                    Brand b1 = brandList.getBrandByID(c1.getBrandID());
                    Brand b2 = brandList.getBrandByID(c2.getBrandID());
                    String name1;
                    String name2;
                    if (b1 != null) {
                        name1 = b1.getBrandName();
                    } else {
                        name1 = "";
                    }
                    if (b2 != null) {
                        name2 = b2.getBrandName();
                    } else {
                        name2 = "";
                    }
                    int nameCompare = name1.compareTo(name2);
                    if (nameCompare == 0) {
                        double price1;
                        double price2;
                        if (b1 != null) {
                            price1 = b1.getPrice();
                        } else {
                            price1 = 0;
                        }
                        if (b2 != null) {
                            price2 = b2.getPrice();
                        } else {
                            price2 = 0;
                        }
                        return Double.compare(price2, price1);
                    }
                    return nameCompare;
                }
                
            };
            Collections.sort(sortedList, carCompare);
            headerForCarList();
            for (Car car : sortedList) {
                printACarDetails(car);
            }
            System.out.println(new String(new char[84]).replace("\0", "-") + "\n");
        }
    }

    public void searchCarByBrandName() {
        String partialName = Validation.getString("Enter a partial brand's name that you want to find: ", "This field cannot be empty!!!!");
        List<Car> result = new ArrayList<>();
        for (Car car : carList) {
            Brand brand = brandList.getBrandByID(car.getBrandID());
            if (brand != null && brand.getBrandName().contains(partialName)) {
                result.add(car);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No car found!!!!\n");
        } else {
            headerForSearchResult();
            for (Car car : result) {
                printACarDetails(car);
            }
            System.out.println(new String(new char[84]).replace("\0", "-") + "\n");
        }
    }

    public void addNewCar() {
        System.out.println("=========================== ADD A NEW CAR ===========================");
        String carID;
        while (true) {
            carID = Validation.getString("Enter car's ID: ", "This field cannot be empty!!!!");
            if (getCarByID(carID) == null) {
                break;
            } else {
                System.out.println("The ID '" + carID + "' has been existed!!!!");
            }
        }
        System.out.println("The list below is the list of brand, you must choose 1 from the list!!!!");
        brandList.listAllBrands();
        String brandID;
        while (true) {
            brandID = Validation.getString("Enter brand's ID that you choose from the brand list: ", "This field cannot be empty!!!!");
            if (brandList.getBrandByID(brandID) != null) {
                break;
            } else {
                System.out.println("The brand ID '" + brandID + "' is not exist!!!!");
            }
        }
        String color = Validation.getString("Enter car's color: ", "This field cannot be empty!!!!");
        String frameID;
        while (true) {
            frameID = Validation.getString("Enter car's frame ID (must follow the format 'Fxxxxx'): ", "This field cannot be empty!!!!");
            if (Validation.isValidFrameID(frameID)) {
                if (isFrameIDUnique(frameID)) {
                    break;
                } else {
                    System.out.println("The frame ID '" + frameID + "' has been existed!!!!");
                }
            } else {
                System.out.println("Wrong format!!!!");
            }
        }
        String engineID;
        while (true) {
            engineID = Validation.getString("Enter car's engine ID (must follow the format 'Exxxxx'): ", "This field cannot be empty!!!!");
            if (Validation.isValidEngineID(engineID)) {
                if (isEngineIDUnique(engineID)) {
                    break;
                } else {
                    System.out.println("The engine ID '" + engineID + "' has been existed!!!!");
                }
            } else {
                System.out.println("Wrong format!!!!");
            }
        }
        Car car = new Car(carID, brandID, color, frameID, engineID);
        this.carList.add(car);
        System.out.println("Successfully added to list for car ID: " + carID);
        Validation.dataChanged = true;
    }

    public void removeCar() {
        String carID = Validation.getString("Enter car's ID to remove: ", "This field cannot be empty!!!!");
        Car car = getCarByID(carID);
        if (car == null) {
            System.out.println("The car ID '" + carID + "' is not exist!!!!");
        } else {
            this.carList.remove(car);
            System.out.println("Successfully removed for car ID: " + carID + "\n");
        }
        Validation.dataChanged = true;
    }

    public void updateCar() {
        String carID = Validation.getString("Enter car's ID to update: ", "This field cannot be empty!!!!");
        Car car = getCarByID(carID);
        if (car == null) {
            System.out.println("The car ID '" + carID + "' is not exist!!!!");
        } else {
            System.out.println("===== UPDATE INFORMATION FOR CAR " + carID + " =====");
            System.out.println("(If you don't want to update a field, just press enter on that field.)");
            String newColor = Validation.updateString("Enter new car's name: ", car.getColor());
            car.setColor(newColor);
            String frameID;
            while (true) {
                frameID = Validation.updateString("Enter new car's frame ID (must follow the format 'Fxxxxx'): ", car.getFrameID());
                if (frameID.equals(car.getFrameID())) {
                    break;
                }
                if (Validation.isValidFrameID(frameID)) {
                    if (isFrameIDUnique(frameID)) {
                        car.setFrameID(frameID);
                        break;
                    } else {
                        System.out.println("The frame ID '" + frameID + "' has been existed!!!!");
                    }
                } else {
                    System.out.println("Wrong format!!!!");
                }
            }
            String engineID;
            while (true) {
                engineID = Validation.updateString("Enter new car's engine ID (must follow the format 'Exxxxx'): ", car.getEngineID());
                if (engineID.equals(car.getEngineID())) {
                    break;
                }
                if (Validation.isValidEngineID(engineID)) {
                    if (isEngineIDUnique(engineID)) {
                        car.setEngineID(engineID);
                        break;
                    } else {
                        System.out.println("The engine ID '" + engineID + "' has been existed!!!!");
                    }
                } else {
                    System.out.println("Wrong format!!!!");
                }
            }
        }
        System.out.println(new String(new char[60]).replace("\0", "="));
        System.out.println("Successfully updated for brand ID: " + carID +"\n");
        Validation.dataChanged = true;
    }

    public void listCarByColor() {
        String color = Validation.getString("Enter car's color that you want to find: ", "This field cannot be empty!!!!");
        List<Car> result = new ArrayList<>();
        for (Car car : carList) {
            if (car.getColor().equalsIgnoreCase(color)) {
                result.add(car);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No cars found this color " + color);
        } else {
            headerForSearchResult();
            for (Car car : result) {
                printACarDetails(car);
            }
            System.out.println(new String(new char[84]).replace("\0", "-") + "\n");
        }
    }

    public void saveDataToFile() {
        carData.saveCarList(carList, fileName);
    }
}
