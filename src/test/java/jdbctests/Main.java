package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String oracleDBURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String oracleDBUsername = "hr";
        String oracleDBPassword = "hr";

        Connection connection = DriverManager.getConnection(oracleDBURL, oracleDBUsername, oracleDBPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

        resultSet.next(); // move to first row in results
        System.out.println(resultSet.getString("REGION_ID"));
        System.out.println(resultSet.getString("REGION_NAME"));

        resultSet.next();
        System.out.println(resultSet.getString("REGION_ID"));
        System.out.println(resultSet.getString("REGION_NAME"));


        // Close Connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
