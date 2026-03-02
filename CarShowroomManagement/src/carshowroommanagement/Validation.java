/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carshowroommanagement;

import java.util.Scanner;

/**
 *
 * @author asus
 */
public class Validation {
    private static final String FRAME_ID_PATTERN = "F\\d{5}";
    private static final String ENGINE_ID_PATTERN = "E\\d{5}";
    public static boolean dataChanged = false;
    private static final Scanner sc = new Scanner(System.in);

    // Frame ID must follow the format: "F00000"
    public static boolean isValidFrameID(String frameID) {
        return frameID != null && frameID.matches(FRAME_ID_PATTERN);
    }

    // Engine ID must follow the format: "E00000"
    public static boolean isValidEngineID(String engineID) {
        return engineID != null && engineID.matches(ENGINE_ID_PATTERN);
    }

    // Brand ID in Car must not be null or empty
    public static boolean isValidCarBrandID(String brandID) {
        return brandID != null && !brandID.trim().isEmpty();
    }

    public static String getString(String welcome, String error) {
        String result = null;
        do {
            System.out.print(welcome);
            result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println(error);
            }
        } while (result.isEmpty());
        return result;
    }

    public static String updateString(String welcome, String oldValue) {
        String result = oldValue;
        System.out.print(welcome);
        result = sc.nextLine();
        if (result.isEmpty()) {
            result = oldValue;
        }
        return result;
    }

    public static double getDouble(String welcome, String error) {
        double result = 0;
        boolean contionous = true;
        do {
            System.out.print(welcome);
            try {
                result = Double.parseDouble(sc.nextLine().trim());
                if (result <= 0) {
                    System.out.println(error);
                } else {
                    contionous = false;
                }
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (contionous);
        return result;
    }

    public static double updateDouble(String welcome, String error, double oldValue) {
        double result = oldValue;
        boolean contionous = true;
        do {
            System.out.print(welcome);
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    result = oldValue;
                    contionous = false;
                } else {
                    result = Double.parseDouble(input);
                    if (result <= 0) {
                        System.out.println(error);
                    } else {
                        contionous = false;
                    }
                }
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (contionous);
        return result;
    }

    public static int getInt(String welcome, String error, int min, int max) {
        int result = 0;
        boolean continous = true;
        do {
            System.out.print(welcome);
            try {
                result = Integer.parseInt(sc.nextLine().trim());
                if (result > max || result < min) {
                    System.out.println("Choice must be between " + min + " and " + max + ".");
                } else {
                    continous = false;
                }
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (continous);
        return result;
    }

    public static String getUpdateStringByRegex(String message, String oldValue, String error_message, String regex) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return oldValue;
            }
            
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println(error_message);
            }
        }
    }

    public static boolean confirmYesNo(String welcome) {
        String input = getString(welcome, "Please enter Y or N.").trim();
        return input.equalsIgnoreCase("Y");
    }

    public static boolean isDataChanged() {
        return dataChanged;
    }
}
