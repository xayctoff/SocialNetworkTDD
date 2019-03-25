package test;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void userClassCreationTest() {
        User user = new User();
        Assert.assertNotNull(user);
    }

}
