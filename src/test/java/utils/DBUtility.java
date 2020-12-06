package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
    private static final String oracleDBURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String oracleDBUsername = "hr";
    private static final String oracleDBPassword = "hr";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(oracleDBURL, oracleDBUsername, oracleDBPassword);
    }
}
