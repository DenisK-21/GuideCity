package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>(record());
        print(cities);
    }

    public static void print(List<City> cities) {
        for (City city : cities) {
            System.out.println(city.getString());
        }
    }

    public static List<City> record() {
        List<City> link = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("Задача ВС Java Сбер.csv"));

            while (scan.hasNextLine()) {

                Scanner str = new Scanner(scan.nextLine());
                String[] def = new String[6];
                str.useDelimiter(";");
                int ind = 0;
                while (str.hasNext()) {
                    def[ind] = str.next();
                    ind++;
                }
                link.add(new City(def[1], def[2], def[3], Integer.parseInt(def[4]), def[5]));
                str.close();
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return link;
    }
}
