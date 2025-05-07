package org.example;

import java.sql.*;

public class ContinentDao {
    private final Connection conn;
    public ContinentDao(Connection conn) { this.conn = conn; }

    public Continent create(Continent c) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "INSERT INTO continents(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, c.getName());
        st.executeUpdate();
        ResultSet keys = st.getGeneratedKeys();
        if (keys.next()) c.setId(keys.getInt(1));
        return c;
    }

    public Continent findByName(String name) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT id, name FROM continents WHERE name=?");
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        return rs.next() ? new Continent(rs.getInt("id"), rs.getString("name")) : null;
    }
}
