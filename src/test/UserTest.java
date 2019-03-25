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

}
