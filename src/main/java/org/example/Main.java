package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DbConnection.get()) {
            InputStream in = Main.class.getClassLoader().getResourceAsStream("schema.sql");
            String sql = new String(in.readAllBytes());
            for (String stmt : sql.split(";")) {
                if (!stmt.isBlank()) conn.createStatement().execute(stmt);
            }

            CityDao cityDao = new CityDao(conn);
            List<City> cities = cityDao.findAll();

            if (cities.isEmpty()) {
                System.out.println("No cities found in database. Importing from CSV...");
                CityImporter importer = new CityImporter(conn);
                importer.importFromCSV();
                cities = cityDao.findAll();
                System.out.println("Cities imported.");
            } else {
                System.out.println("Found " + cities.size() + " cities in database.");
            }

            displaySampleDistances(cities);

            interactiveDistanceCalculator(cityDao);
        }
    }

    private static void displaySampleDistances(List<City> cities) {
        System.out.println("\n=== Sample Distances Between World Cities ===");

        // Display distances between some major cities if we have enough cities
        if (cities.size() >= 2) {
            for (int i = 0; i < Math.min(5, cities.size() - 1); i++) {
                City city1 = cities.get(i);
                City city2 = cities.get(i + 1);
                double distance = city1.distanceTo(city2);
                System.out.printf("Distance from %s (%s) to %s (%s): %.2f km%n", 
                    city1.getName(), city1.getCountry().getCode(),
                    city2.getName(), city2.getCountry().getCode(),
                    distance);
            }
        }
    }

    private static void interactiveDistanceCalculator(CityDao cityDao) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== City Distance Calculator ===");
        System.out.println("Enter 'exit' at any prompt to quit.");

        while (true) {
            try {
                // Get first city
                System.out.print("\nEnter first city name (or part of name): ");
                String cityName1 = scanner.nextLine().trim();
                if (cityName1.equalsIgnoreCase("exit")) break;

                List<City> cities1 = cityDao.findByName(cityName1);
                if (cities1.isEmpty()) {
                    System.out.println("No cities found with that name.");
                    continue;
                }

                City city1 = selectCity(cities1, scanner);
                if (city1 == null) continue;

                // Get second city
                System.out.print("\nEnter second city name (or part of name): ");
                String cityName2 = scanner.nextLine().trim();
                if (cityName2.equalsIgnoreCase("exit")) break;

                List<City> cities2 = cityDao.findByName(cityName2);
                if (cities2.isEmpty()) {
                    System.out.println("No cities found with that name.");
                    continue;
                }

                City city2 = selectCity(cities2, scanner);
                if (city2 == null) continue;

                // Calculate and display distance
                double distance = city1.distanceTo(city2);
                System.out.printf("\nDistance from %s (%s) to %s (%s): %.2f km%n", 
                    city1.getName(), city1.getCountry().getCode(),
                    city2.getName(), city2.getCountry().getCode(),
                    distance);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }

    private static City selectCity(List<City> cities, Scanner scanner) {
        if (cities.size() == 1) return cities.get(0);

        System.out.println("\nMultiple cities found. Please select one:");
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            System.out.printf("%d. %s, %s (%s)%n", 
                i + 1, city.getName(), city.getCountry().getName(), city.getCountry().getCode());
        }

        while (true) {
            System.out.print("Enter number (or 'cancel'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("cancel")) return null;

            try {
                int selection = Integer.parseInt(input);
                if (selection >= 1 && selection <= cities.size()) {
                    return cities.get(selection - 1);
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
