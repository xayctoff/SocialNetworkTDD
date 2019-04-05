package test;

import model.Database;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTest {

    @Test
    public void databaseConnectionTest() throws SQLException {
        Database database = Database.getInstance();
        Assert.assertNotNull(database);
    }

    @Test
    public void insertTest() throws SQLException {
        Database database = Database.getInstance();
        String query = "INSERT INTO status values (DEFAULT, 'Заявка отправлена')";
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

    @Test
    public void searchPeopleTest() throws SQLException{
        Database database = Database.getInstance();
        ArrayList <String> result = database.searchPeople("xayctoff");
        Assert.assertNotNull(result);
    }

    @Test
    public void checkOnFriendshipTestFailure() throws SQLException {
        Database database = Database.getInstance();
        boolean result = database.checkOnFriendship("xayctoff", "andre");
        Assert.assertFalse(result);
    }

    @Test
    public void updateTest() throws SQLException {
        Database database = Database.getInstance();
        String query = "UPDATE friends SET status = 2 WHERE first = (SELECT user_id FROM users WHERE login = " +
                "'xayctoff') AND second = (SELECT user_id FROM users WHERE login = 'andre') OR first = (SELECT user_id" +
                " FROM users WHERE login = 'andre') AND second = (SELECT user_id FROM users WHERE login = 'xayctoff')";
        int result = database.update(query);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getUserIdTest() throws SQLException {
        Database database = Database.getInstance();
        int result = database.getUserId("jenek41");
        Assert.assertEquals(9, result);
    }

    @Test
    public void getFriendsListTest() throws SQLException {
        Database database = Database.getInstance();
        ArrayList <String> result = database.getFriendsList("xayctoff");
        Assert.assertNotNull(result);
    }

    @Test
    public void getFriendsListCorrectSizeTest() throws SQLException {
        Database database = Database.getInstance();
        ArrayList <String> result = database.getFriendsList("xayctoff");
        Assert.assertEquals(2, result.size());
    }

}