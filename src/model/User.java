package model;

import java.sql.SQLException;

public class User {

    private String login;
    private String password;

    public User() {}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean addFriend(String first, String second) throws SQLException {
        if (!Database.getInstance().checkOnFriendship(first, second)) {
            Database.getInstance().insert("INSERT INTO friends VALUES ((SELECT user_id FROM users WHERE login = "
                    + "'" + first + "'), (SELECT user_id FROM users WHERE login = '" + second + "'), 1)");
            return true;
        }

        else {
            return false;
        }

    }
}
