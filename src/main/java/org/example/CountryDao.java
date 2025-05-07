package org.example;

import java.sql.*;

public class CountryDao {
    private final Connection conn;
    public CountryDao(Connection conn) { this.conn = conn; }

    public Country create(Country c) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "INSERT INTO countries(name, code, continent_id) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        st.setString(1, c.getName());
        st.setString(2, c.getCode());
        st.setInt(3, c.getContinent().getId());
        st.executeUpdate();
        ResultSet keys = st.getGeneratedKeys();
        if (keys.next()) c.setId(keys.getInt(1));
        return c;
    }

    public Country findByCode(String code) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT c.id, c.name, c.code, ct.id ct_id, ct.name ct_name " +
                        "FROM countries c JOIN continents ct ON c.continent_id = ct.id WHERE c.code = ?");
        st.setString(1, code);
        ResultSet rs = st.executeQuery();
        if (!rs.next()) return null;
        Continent cont = new Continent(rs.getInt("ct_id"), rs.getString("ct_name"));
        return new Country(rs.getInt("id"), rs.getString("name"), code, cont);
    }
}
