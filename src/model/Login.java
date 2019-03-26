package model;

import java.sql.SQLException;

public class Login {

    public Login() {}

    public boolean signUp(String login, String password) throws SQLException {
        Database database = Database.getInstance();

        if (database.checkOnExistUser(login)) {
            return false;
        }

        else {
            database.insert("INSERT INTO users values('" + login + "', '" + password + "')");
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            return true;
        }
    }
}
