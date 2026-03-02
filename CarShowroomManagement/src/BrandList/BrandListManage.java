/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BrandList;

import carshowroommanagement.Validation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class BrandListManage {
    private static BrandListManage list;
    private List<Brand> brandList;
    private IBrandData brandData;
    private String fileName = "brands.txt";

    private BrandListManage() {
        this.brandData = new BrandFile();
        this.brandList = brandData.loadBrandList(fileName);
    }

    public static BrandListManage getList() {
        if (list == null) {
            list = new BrandListManage();
        }
        return list;
    }

    public List<Brand> getBrandList() {
        return this.brandList;
    }

    private void headerForBrandList() {
        System.out.println("\n======================================= BRAND LIST =======================================");
        System.out.printf(String.format("%-10s | %-40s | %-20s | %s\n", "ID", "Brand's name", "Sound brand", "Price"));
        System.out.println(new String(new char[90]).replace("\0", "-"));
    }

    private void headerForSearchResult() {
        System.out.println("\n===================================== SEARCH RESULTS =====================================");
        System.out.printf(String.format("%-10s | %-40s | %-20s | %s\n", "ID", "Brand's name", "Sound brand", "Price"));
        System.out.println(new String(new char[90]).replace("\0", "-"));

    }

    private void printBrandListDetails(List<Brand> list) {
        for (Brand brand: list) {
                System.out.println(brand.toString());
        }
    }

    public void listAllBrands() {
        if (brandList.isEmpty()) {
            System.out.println("Brand List is empty!!!!\n");
        } else {
            headerForBrandList();
            printBrandListDetails(brandList);
            System.out.println(new String(new char[90]).replace("\0", "-") +"\n");
        }
    }

    public Brand getBrandByID(String brandID) {
        Brand result = null;
        if (brandID == null || brandID.trim().isEmpty()) {
            result = null;
        }
        for (Brand brand : brandList) {
            if (brand.getBrandID().equalsIgnoreCase(brandID.trim())) {
                result = brand;
            }
        }
        return result;
    }

    public void addNewBrand() {
        System.out.println("=========================== ADD A NEW BRAND ===========================");
        String brandID;
        while (true) {
            brandID = Validation.getString("Enter brand's ID: ", "This field cannot be empty!!!!");
            if (getBrandByID(brandID) == null) {
                break;
            } else {
                System.out.println("The ID '" + brandID + "' has been existed!!!!");
            }
        }
        String brandName = Validation.getString("Enter brand's name: ", "This field cannot be empty!!!!");
        String soundBrand = Validation.getString("Enter sound's brand: ", "This field cannot be empty!!!!");
        double price = Validation.getDouble("Enter brand's price (Billion): ", "This field cannot be empty!!!!");
        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
        this.brandList.add(newBrand);
        System.out.println("Successfully added to list for brand ID: " + brandID +"\n");
        Validation.dataChanged = true;
    }

    public void searchBrandByID() {
        String brandID = Validation.getString("Enter brand's ID: ", "This field cannot be empty!!!!");
        Brand brand = getBrandByID(brandID);
        if (brand == null) {
            System.out.println("This brand does not exist!!!!\n");
        } else {
            headerForSearchResult();
            System.out.println(brand.toString());
            System.out.println(new String(new char[90]).replace("\0", "-") + "\n");
        }
    }

    public void updateBrand() {
        String brandID = Validation.getString("Enter brand's ID to update: ", "This field cannot be empty!!!!");
        Brand brand = getBrandByID(brandID);
        if (brand == null) {
            System.out.println("This brand does not exist!!!!\n");
        } else {
            System.out.println("===== UPDATE INFORMATION FOR BRAND " + brandID + " =====");
            System.out.println("(If you don't want to update a field, just press enter on that field.)");
            String newName = Validation.updateString("Enter new brand's name: ", brand.getBrandName());
            String newSoundBrand = Validation.updateString("Enter new sound's brand: ", brand.getSoundBrand());
            double newPrice = Validation.updateDouble("Enter new brand's price (Billion): ", "Invalid Price!!!", brand.getPrice());
            brand.setBrandName(newName);
            brand.setSoundBrand(newSoundBrand);
            brand.setPrice(newPrice);
            System.out.println(new String(new char[60]).replace("\0", "="));
            System.out.println("Successfully updated for brand ID: " + brandID + "\n");
        }
        Validation.dataChanged = true;
    }

    public void listBrandByPrice() {
        double inputPrice = Validation.getDouble("Enter a maximum price (Billion): ", "This field cannot be empty!!!!");
        List<Brand> result = new ArrayList<>();
        for (Brand brand : brandList) {
            if (brand.getPrice() <= inputPrice) {
                result.add(brand);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No brand has the price under " + inputPrice + "B.");
        } else {
            headerForSearchResult();
            System.out.println("                          (Brand has the price under " + inputPrice + "B)");
            printBrandListDetails(result);
            System.out.println(new String(new char[90]).replace("\0", "-") + "\n");
        }
    }

    public void saveDataToFile() {
        brandData.saveBrandList(brandList, fileName);
    }
}
