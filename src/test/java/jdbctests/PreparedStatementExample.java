package jdbctests;

import org.junit.Assert;
import org.junit.Test;
import utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreparedStatementExample {

    @Test
    public void preparedStatementExample(){

        List<String> countries = new ArrayList<>();

        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtility.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM countries");
            resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(resultSet.getString("country_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (prepStatement != null && !prepStatement.isClosed()) {
                    prepStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Assert.assertEquals(25, countries.size());
        Assert.assertTrue(countries.contains("Zimbabwe"));
        System.out.println(countries.toString());

    }

    @Test
    public void preparedStatementWithIndex(){

        List<String> countries = new ArrayList<>();

        Connection connection = null;
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtility.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM countries WHERE region_id = ?");
            prepStatement.setInt(1, 2); // gets the first question mark and sets it to 2
            resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(resultSet.getString("country_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (prepStatement != null && !prepStatement.isClosed()) {
                    prepStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        List<String> expectedCountries = Arrays.asList("Argentina", "Brazil", "Canada", "Mexico", "United States of America");

        Assert.assertEquals(5, countries.size());
        Assert.assertTrue(countries.contains("Canada"));
        System.out.println(countries.toString());

        Assert.assertEquals(expectedCountries, countries);

    }

}
