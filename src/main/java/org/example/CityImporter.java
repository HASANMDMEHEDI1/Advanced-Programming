package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class CityImporter {
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final ContinentDao continentDao;

    public CityImporter(Connection conn) {
        this.cityDao = new CityDao(conn);
        this.countryDao = new CountryDao(conn);
        this.continentDao = new ContinentDao(conn);
    }

    public void importFromCSV() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("worldcities.csv");
        if (is == null) {
            throw new IllegalArgumentException("CSV file not found in resources!");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        reader.readLine(); // Skip header

        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (fields.length < 6) continue;

            String countryName = fields[0].replaceAll("\"", "");
            String cityName = fields[1].replaceAll("\"", "");
            double lat = Double.parseDouble(fields[2]);
            double lon = Double.parseDouble(fields[3]);
            String countryCode = fields[4].replaceAll("\"", "");
            String continentName = fields[5].replaceAll("\"", "");

            // All cities in this dataset are capitals
            boolean capital = true;

            // Skip entries with NULL country code
            if (countryCode.equals("NULL")) continue;

            try {
                // Find or create continent
                Continent continent = findOrCreateContinent(continentName);

                // Find or create country
                Country country = findOrCreateCountry(countryName, countryCode, continent);

                // Create city
                City city = new City(country, cityName, capital, lat, lon);
                cityDao.create(city);
                count++;
            } catch (Exception e) {
                System.err.println("Error importing city: " + cityName + " - " + e.getMessage());
            }
        }

        System.out.println("Imported " + count + " cities.");
        reader.close();
    }

    private Continent findOrCreateContinent(String name) throws SQLException {
        // This is a simple implementation. In a real application, you might want to cache continents.
        Continent continent = continentDao.findByName(name);
        if (continent == null) {
            continent = continentDao.create(new Continent(name));
        }
        return continent;
    }

    private Country findOrCreateCountry(String name, String code, Continent continent) throws SQLException {
        // This is a simple implementation. In a real application, you might want to cache countries.
        Country country = countryDao.findByCode(code);
        if (country == null) {
            country = countryDao.create(new Country(name, code, continent));
        }
        return country;
    }
}
