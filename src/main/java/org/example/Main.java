package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>(record());
        print(cities);

        // Comparator: сортировка по наименованию без учета регистра
        List<City> sortNameComparator = sortNameComparator(cities);
        print(sortNameComparator);

        // Lambda: сортировка по наименованию без учета регистра
        List<City> sortNameLambda = sortNameLambda(cities);
        print(sortNameLambda);

        // Comparator: сортировка по федеральному округу и наименованию города внутри каждого федерального округа с учётом регитсра
        List<City> sortDistrictNameComparator = sortDistrictNameComparator(cities);
        print(sortDistrictNameComparator);

        // Lambda: сортировка по федеральному округу и наименованию города внутри каждого федерального округа с учётом регитсра
        List<City> sortDistrictNameLambda = sortDistrictNameLambda(cities);
        print(sortDistrictNameLambda);

        //нахождение преобразование списка в массив и индекса и зачение макс кол-ва жителей
        City[] arrCities = convert(cities);
        Pair<Integer, Integer> max = max(arrCities);
        System.out.println("[" + max.getValue1() + "] = " + max.getValue2());

        //определение городов в каждом регионе
        List<Pair<String, Integer>> find = number_of_cities_in_the_region(cities);
        for (Pair<String,Integer> ob:
             find) {
            System.out.println(ob.getValue1()+" - " + ob.getValue2());
            
        }


    }

    // определение городов в каждой области
    public static List<Pair<String, Integer>> number_of_cities_in_the_region(List<City> cities) {
        List<Pair<String, Integer>> find = new ArrayList<>();
        cities.stream().collect(Collectors.groupingBy(City::getRegion, Collectors.counting())).forEach((s, count)
                -> find.add(new Pair<>(s, Math.toIntExact(count))));
        return find;
    }

    //нахождение преобразование списка в массив
    public static City[] convert(List<City> cities) {
        return cities.toArray(new City[0]);
    }

    //нахождение индекса и зачение макс кол-ва жителей
    public static Pair<Integer, Integer> max(City[] arr) {
        Pair<Integer, Integer> max = new Pair<>(0, 0);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getPopulation() > max.getValue2()) {
                max.setValue1(i);
                max.setValue2(arr[i].getPopulation());
            }
        }
        return max;
    }

    // Comparator: сортировка по наименованию городов без учёта регистра
    public static List<City> sortNameComparator(List<City> cities) {
        return cities.stream().sorted(new Comparator<>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        }).toList();

    }

    // Lambda:  сортировка по наименованию городов без учёт регистров
    public static List<City> sortNameLambda(List<City> cities) {
        return cities.stream().sorted((City o1, City o2) -> o1.getName().compareToIgnoreCase(o2.getName())).toList();
    }

    // Comparator: сортировка по федеральному округу и наименованию города внутри каждого федерального округа с учётом регитсра
    public static List<City> sortDistrictNameComparator(List<City> cities) {
        return cities.stream().sorted(Comparator.comparing(City::getName))
                .sorted(Comparator.comparing(City::getDistrict)).toList();
    }

    // Lambda: сортировка по федеральному округу и наименованию города внутри каждого федерального округа с учётом регитсра
    public static List<City> sortDistrictNameLambda(List<City> cities) {
        return cities.stream().sorted((o1, o2) -> {
            int result = o1.getDistrict().compareTo(o2.getDistrict());
            if (result == 0)
                return o1.getName().compareTo(o2.getName());
            return result;
        }).toList();
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
