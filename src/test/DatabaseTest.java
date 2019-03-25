package test;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {

    @Test
    public void databaseConnectionTest() throws SQLException {
        String username = "postgres";
        String password = "111";
        String url = "jdbc:postgresql://localhost:5432/network";

        Connection connection;
        connection = DriverManager.getConnection(url, username, password);
        Assert.assertNotNull(connection);
    }
}
