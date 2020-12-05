package jdbctests;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JDBC_Examples {
        public final String oracleDBURL = "jdbc:oracle:thin:@localhost:1521:xe";
        public final String oracleDBUsername = "hr";
        public final String oracleDBPassword = "hr";

        @Test
        public void readRegionNames() throws SQLException {
            Connection connection = DriverManager.getConnection(oracleDBURL, oracleDBUsername, oracleDBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT region_name FROM regions");

            // looping through results of region_name
            while (resultSet.next()) {
                String resName = resultSet.getString("region_name");
                System.out.println(resName);
            }

            // ===========================================================
            System.out.println("-------------------------***--------------------------");

            resultSet = statement.executeQuery("SELECT region_id, region_name FROM regions");
            while (resultSet.next()) {
                int resID = resultSet.getInt(1);
                String resName = resultSet.getString(2);
                System.out.println(resID + " | " + resName);
            }



            resultSet.close();
            statement.close();
            connection.close();
        }


}


