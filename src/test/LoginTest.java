package test;

import model.Login;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class LoginTest {

    @Test
    public void loginClassCreationTest() {
        Login login = new Login();
        Assert.assertNotNull(login);
    }

    @Test
    public void signUpSuccessTest() throws SQLException {
        String login = "xayctoff";
        String password = "111";
        Login newUser = new Login();
        int result = newUser.signUp(login, password);
        Assert.assertEquals(1, result);
    }

    @Test
    public void signUpFailureTest() throws SQLException {
        String login = "xayctoff";
        String password = "111";
        Login newUser = new Login();
        int result = newUser.signUp(login, password);
        Assert.assertEquals(0, result);
    }

    @Test
    public void signInTest() {
        String login = "xayctoff";
        String password = "111";
        Login authorization = new Login();
        int result = authorization.signIn(login, password);
        Assert.assertEquals(1, result);
    }

}
