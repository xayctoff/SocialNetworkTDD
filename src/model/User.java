package model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

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

    public boolean confirmFriendship(String first, String second, boolean decision) throws SQLException {
        if (decision) {
            Database.getInstance().update("UPDATE friends SET status = 2 WHERE first = (SELECT user_id FROM " +
                    "users WHERE login = '" + first + "') AND second = (SELECT user_id FROM users WHERE login = '" +
                    second + "')");
            return true;
        }

        else {
            Database.getInstance().update("UPDATE friends SET status = 0 WHERE first = (SELECT user_id FROM " +
                    "users WHERE login = '" + first + "') AND second = (SELECT user_id FROM users WHERE login = '" +
                    second + "')");
            return false;
        }
    }

    public int writeMessage(String server, String receiver, String message) throws SQLException {
        Database database = Database.getInstance();
        int serverId = database.getUserId(server);
        int receiverId = database.getUserId(receiver);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = new Date();

        return database.insert("INSERT INTO messages VALUES (" + serverId + ", " + receiverId + ", '" + message +
                "', " + "'" + dateFormat.format(date) + "')");
    }
}
