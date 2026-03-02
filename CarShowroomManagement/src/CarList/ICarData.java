/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CarList;

import java.util.List;

/**
 *
 * @author asus
 */
public interface ICarData {
    List<Car>  loadCarList(String path);
    int saveCarList(List<Car> carList, String path);
}
