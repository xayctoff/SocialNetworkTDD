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
        boolean result = database.insert("INSERT INTO status values (2, 'В друзьях')");
        Assert.assertTrue(result);
    }


}
