package test;

import model.Database;
import model.Login;
import model.User;
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
    public void signUpTest() throws SQLException {
        String login = "xayctoff";
        String password = "111";
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        int result = Database.getInstance().insert("INSERT INTO users values (1, @login, @password");
        Assert.assertEquals(1, result);
    }

}
