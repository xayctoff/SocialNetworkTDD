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
        String login = "jenek41";
        String password = "porlkks2334";
        Login newUser = new Login();
        boolean result = newUser.signUp(login, password);
        Assert.assertTrue(result);
    }

    @Test
    public void signUpFailureTest() throws SQLException {
        String login = "xayctoff";
        String password = "111";
        Login newUser = new Login();
        boolean result = newUser.signUp(login, password);
        Assert.assertFalse(result);
    }

    @Test
    public void signInSuccessTest() throws SQLException {
        String login = "xayctoff";
        String password = "111";
        Login authorization = new Login();
        boolean result = authorization.signIn(login, password);
        Assert.assertTrue(result);
    }

    @Test
    public void signInFailureTest() throws SQLException {
        String login = "pedro";
        String password = "111";
        Login authorization = new Login();
        boolean result = authorization.signIn(login, password);
        Assert.assertFalse(result);
    }

}
