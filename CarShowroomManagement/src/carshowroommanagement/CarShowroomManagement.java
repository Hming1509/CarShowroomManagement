/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carshowroommanagement;

import CarList.CarListManage;
import BrandList.BrandListManage;

/**
 *
 * @author asus
 */
public class CarShowroomManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BrandListManage brandList = BrandListManage.getList();
        CarListManage carList = CarListManage.getList(brandList);
        int choice;
        do {
            System.out.println(new String(new char[90]).replace("\0", "="));
            System.out.println("--                               Car Showroom Management                                --");
            System.out.println(new String(new char[90]).replace("\0", "="));
            System.out.println("|1. List all rands.                                                                      |");
            System.out.println("|2. Add a new brand.                                                                     |");
            System.out.println("|3. Search for a brand ID.                                                               |");
            System.out.println("|4. Update a brand by ID.                                                                |");
            System.out.println("|5. List all brands with prices less than or equal to an input value.                    |");
            System.out.println("|6. List all cars in ascending order of brand names.                                     |");
            System.out.println("|7. Search cars by partial brand name match.                                             |");
            System.out.println("|8. Add a new car.                                                                       |");
            System.out.println("|9. Remove a car by ID.                                                                  |");
            System.out.println("|10. Update a car by ID.                                                                 |");
            System.out.println("|11. List all cars by a specific color.                                                  |");
            System.out.println("|12. Save data to files.                                                                 |");
            System.out.println("|13. Quit program.                                                                       |");
            System.out.println(new String(new char[90]).replace("\0", "="));
            choice = Validation.getInt("Enter your choice: ", "Invalid number, please enter another number!!!!", 1, 13);
            switch (choice) {
                case 1:
                    brandList.listAllBrands();
                    break;
                case 2:
                    brandList.addNewBrand();
                    break;
                case 3:
                    brandList.searchBrandByID();
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.listBrandByPrice();
                    break;
                case 6:
                    carList.listAllCarsSorted();
                    break;
                case 7:
                    carList.searchCarByBrandName();;
                    break;
                case 8:
                    carList.addNewCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.listCarByColor();
                    break;
                case 12:
                    brandList.saveDataToFile();
                    carList.saveDataToFile();
                    System.out.println("Saving data completed!!!\n");
                    break;
                case 13:
                    if (Validation.isDataChanged()) {
                        if (Validation.confirmYesNo("Do you want to save the changes before exiting? (Y/N): ")) {
                            brandList.saveDataToFile();
                            carList.saveDataToFile();
                            System.out.println("Saving data completed!!!!");
                        }
                    }
                    System.out.println("Goodbye^^");
                    System.exit(0);
            }

        } while (choice != 13);
    }
}
