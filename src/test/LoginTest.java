package test;

import org.junit.Assert;
import org.junit.Test;

public class LoginTest {

    @Test
    void loginClassCreationTest() {
        Login login = new Login();
        Assert.assertNotNull(login);
    }

}
