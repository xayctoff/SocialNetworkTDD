package test;

import model.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DatabaseTest {

    @Test
    public void databaseConnectionTest() throws SQLException {
        Database database = Database.getInstance();
        Assert.assertNotNull(database);
    }

    @Test
    public void insertTest() throws SQLException {
        Database database = Database.getInstance();
        String query = "INSERT INTO status values (DEFAULT, 'Нет связи')";
        int result = database.insert(query);
        Assert.assertEquals(1, result);
    }

    @Test
    public void checkOnExistUserTest() throws SQLException {
        Database database = Database.getInstance();
        boolean result = database.checkOnExistUser("xayctoff");
        Assert.assertTrue(result);
    }

    @Test
    public void checkOnValidAuthorizationTest() throws SQLException {
        Database database = Database.getInstance();
        boolean result = database.checkOnValidAuthorization("xayctoff", "111");
        Assert.assertTrue(result);
    }

}