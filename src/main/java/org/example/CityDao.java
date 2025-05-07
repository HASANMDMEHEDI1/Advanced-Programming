package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    private final Connection conn;
    public CityDao(Connection conn) { this.conn = conn; }

    public City create(City city) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cities(name, country_id, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        st.setString(1, city.getName());
        st.setInt(2, city.getCountry().getId());
        st.setBoolean(3, city.isCapital());
        st.setDouble(4, city.getLatitude());
        st.setDouble(5, city.getLongitude());
        st.executeUpdate();
        ResultSet keys = st.getGeneratedKeys();
        if (keys.next()) city.setId(keys.getInt(1));
        return city;
    }

    public City findById(int id) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT c.id, c.name, c.capital, c.latitude, c.longitude, " +
                "co.id as country_id, co.name as country_name, co.code as country_code, " +
                "ct.id as continent_id, ct.name as continent_name " +
                "FROM cities c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents ct ON co.continent_id = ct.id " +
                "WHERE c.id = ?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (!rs.next()) return null;

        return createCityFromResultSet(rs);
    }

    public List<City> findByName(String name) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT c.id, c.name, c.capital, c.latitude, c.longitude, " +
                "co.id as country_id, co.name as country_name, co.code as country_code, " +
                "ct.id as continent_id, ct.name as continent_name " +
                "FROM cities c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents ct ON co.continent_id = ct.id " +
                "WHERE c.name LIKE ?");
        st.setString(1, "%" + name + "%");
        return getCitiesFromResultSet(st);
    }

    public List<City> findByCountry(String countryCode) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT c.id, c.name, c.capital, c.latitude, c.longitude, " +
                "co.id as country_id, co.name as country_name, co.code as country_code, " +
                "ct.id as continent_id, ct.name as continent_name " +
                "FROM cities c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents ct ON co.continent_id = ct.id " +
                "WHERE co.code = ?");
        st.setString(1, countryCode);
        return getCitiesFromResultSet(st);
    }

    public List<City> findCapitals() throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT c.id, c.name, c.capital, c.latitude, c.longitude, " +
                "co.id as country_id, co.name as country_name, co.code as country_code, " +
                "ct.id as continent_id, ct.name as continent_name " +
                "FROM cities c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents ct ON co.continent_id = ct.id " +
                "WHERE c.capital = true");
        return getCitiesFromResultSet(st);
    }

    public List<City> findAll() throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT c.id, c.name, c.capital, c.latitude, c.longitude, " +
                "co.id as country_id, co.name as country_name, co.code as country_code, " +
                "ct.id as continent_id, ct.name as continent_name " +
                "FROM cities c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents ct ON co.continent_id = ct.id");
        return getCitiesFromResultSet(st);
    }

    private List<City> getCitiesFromResultSet(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<City> cities = new ArrayList<>();
        while (rs.next()) {
            cities.add(createCityFromResultSet(rs));
        }
        return cities;
    }

    private City createCityFromResultSet(ResultSet rs) throws SQLException {
        Continent continent = new Continent(
                rs.getInt("continent_id"),
                rs.getString("continent_name"));

        Country country = new Country(
                rs.getInt("country_id"),
                rs.getString("country_name"),
                rs.getString("country_code"),
                continent);

        return new City(
                rs.getInt("id"),
                country,
                rs.getString("name"),
                rs.getBoolean("capital"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"));
    }
}
