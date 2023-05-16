package database;

import java.sql.*;

public class dbconnection {
    public dbconnection() {
        Connection conn = null;
        try {
            String url = "jbdc:unanaccess://database/library.accdb";
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}
