/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class CarFile implements ICarData{
    @Override
    public List<Car> loadCarList(String path) {
        List<Car> list = new ArrayList<>();
        FileReader f = null;
        BufferedReader r = null;
        try {
           File fileCheck = new File(path);
           if(fileCheck.exists() && fileCheck.isFile()){
               f = new FileReader(path);
               r = new BufferedReader(f);
               while (r.ready()){
                   String s = r.readLine();
                   String[] arr = s.split(",");
                   if (arr.length == 5){
                        String carID = arr[0].trim();
                        String brandID = arr[1].trim();
                        String color = arr[2].trim();
                        String frameID = arr[3].trim();
                        String engineID = arr[4].trim();
                        Car c = new Car(carID, brandID, color, frameID, engineID);
                        list.add(c);
                   }
               }
           }
        } catch (Exception e) {
            System.out.println("Error Loading file!!!!!!");
        } finally {
            try {
                if(f!=null) f.close();
                if(r!=null) r.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    @Override
    public int saveCarList(List<Car> carList, String path) {
        int check = 0;
        PrintWriter f = null;
        try {
            f = new PrintWriter(path);
            for (Car car : carList) {
                String c = car.getCarID() + "," + car.getBrandID() + "," + car.getColor() + "," + car.getFrameID() + "," + car.getEngineID() + "\n";
                f.print(c);
                f.flush();
            }
        } catch (Exception e) {
            check = -1;
        } finally {
            try {
                if(f!=null) f.close();
            } catch (Exception e) {
                check = -2;
            }
        }
        return check;
    }
}
