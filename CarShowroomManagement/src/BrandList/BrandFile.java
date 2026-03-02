/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BrandList;

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
public class BrandFile implements IBrandData{
    @Override
    public List<Brand> loadBrandList(String path) {
        List<Brand> list = new ArrayList<>();
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
                   if (arr.length == 3){
                        String brandID = arr[0].trim();
                        String brandName = arr[1].trim();
                        String[] brandAndPrice = arr[2].split(": ");
                        if (brandAndPrice.length == 2) { //phai co if de tranh crash chuong trinh
                            String soundBrand = brandAndPrice[0].trim();
                            double price = Double.parseDouble(brandAndPrice[1].trim().replace("B", ""));
                            Brand b = new Brand(brandID, brandName, soundBrand, price);
                            list.add(b);
                        }
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
    public int saveBrandList(List<Brand> brandList, String path) {
        int check = 0;
        PrintWriter f = null;
        try {
            f = new PrintWriter(path);
            for (Brand brand : brandList) {
                f.println(brand.toStringToSave());
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
