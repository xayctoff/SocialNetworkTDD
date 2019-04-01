package test;

import model.User;
import org.junit.Assert;
import org.junit.Test;

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

}
