package test;

import model.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserTest {

    @Test
    public void userClassCreationTest() {
        User user = new User();
        Assert.assertNotNull(user);
    }

    @Test
    public void getLoginTest() {
        User user = new User();
        user.setLogin("xayctoff");
        Assert.assertNotNull(user.getLogin());
    }

    @Test
    public void getPasswordTest() {
        User user = new User();
        user.setPassword("121313");
        Assert.assertNotNull(user.getPassword());
    }

    @Test
    public void addFriendTestSuccess() throws SQLException {
        User user = new User();
        boolean result = user.addFriend("xayctoff", "andre");
        Assert.assertTrue(result);
    }

    @Test
    public void addFriendTestFailure() throws SQLException {
        User user = new User();
        boolean result = user.addFriend("xayctoff", "egor228");
        Assert.assertFalse(result);
    }

    @Test
    public void confirmFriendshipTestSuccess() {
        User user = new User();
        boolean result = user.confirmFriendship("xayctoff", "andre", true);
        Assert.assertTrue(result);
    }
}
