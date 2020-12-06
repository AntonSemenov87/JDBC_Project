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

        @Test
        public void countAndNavigations() throws SQLException {
            Connection connection = DriverManager.getConnection(oracleDBURL, oracleDBUsername, oracleDBPassword);
            Statement statement = connection.createStatement
                    (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

            // find out how many records in the resultSet
            resultSet.last(); // moves to last row
            int rowCount = resultSet.getRow(); // read row number
            System.out.println("Number of records: " + rowCount);
            // since we moved to last row - it will show department_name of the last row
            System.out.println("Last department name: " + resultSet.getString(2));
            // OR
            System.out.println("Last department name: " + resultSet.getString("DEPARTMENT_NAME"));

            // Navigate to first record
            resultSet.first();
            System.out.println("First department name: " + resultSet.getString("DEPARTMENT_NAME"));

            resultSet.beforeFirst(); // navigate to name of columns
            // PRINT departments from first to last
            while (resultSet.next()) {
                int deptId = resultSet.getInt("DEPARTMENT_ID");
                String deptName = resultSet.getString("DEPARTMENT_NAME");

                System.out.println("ID: " + deptId + " | Name: " + deptName);
            }

            System.out.println("-----------------*****-------------------");

            // PRINT departments from last to first
            while (resultSet.previous()) {
                int deptId = resultSet.getInt("DEPARTMENT_ID");
                String deptName = resultSet.getString("DEPARTMENT_NAME");

                System.out.println("ID: " + deptId + " | Name: " + deptName);
            }

            // GO TO specific row in resultset
            resultSet.absolute(5);
            System.out.println("Fifth department name: " + resultSet.getString("DEPARTMENT_NAME"));




            resultSet.close();
            statement.close();
            connection.close();
        }


}


