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
    public void signUpTest() {
        String login = "xayctoff";
        String password = "111";
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        
    }

}
