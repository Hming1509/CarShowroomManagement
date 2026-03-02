/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BrandList;

import java.util.List;

/**
 *
 * @author asus
 */
public interface IBrandData {
    List<Brand>  loadBrandList(String path);
    int saveBrandList(List<Brand> brandList, String path);
}
