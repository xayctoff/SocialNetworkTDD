package model;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Database instance;
    private Statement statement;
    private Connection connection;

    private Database() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/network";
        String username = "postgres";
        String password = "111";

        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        }

        catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public int insert(String query) throws SQLException {
        statement = instance.connection.createStatement();
        return statement.executeUpdate(query);
    }

    public boolean checkOnExistUser(String login) throws SQLException {
        statement = instance.connection.createStatement();
        String query = "SELECT COUNT(login) FROM users WHERE login = '" + login + "'";
        ResultSet result = statement.executeQuery(query);
        result.next();
        int count = result.getInt("count");
        return count > 0;
    }

    public boolean checkOnValidAuthorization(String login, String password) throws SQLException {
        statement = instance.connection.createStatement();
        String query = "SELECT COUNT(login) FROM users WHERE login = '" + login + "' AND password = '" + password + "'";

        if (!checkOnExistUser(login)) {
            return false;
        }

        else {
            ResultSet result = statement.executeQuery(query);
            result.next();

            return result.getInt("count") != 0;
        }
    }

    public ArrayList <String> searchPeople(String login) throws SQLException {
        statement = instance.connection.createStatement();
        String query = "SELECT login FROM users WHERE login = '" + login + "'";
        return executeResult(query);

    }

    public boolean checkOnFriendship(String first, String second) throws SQLException {
        statement = instance.connection.createStatement();
        String query = "SELECT first, second, status FROM friends WHERE first =\n" +
                "(SELECT user_id FROM users WHERE login = '" + first + "') AND second = (SELECT user_id FROM users WHERE " +
                "login = '" + second + "')OR\nfirst = (SELECT user_id FROM users WHERE login = '" + second + "') " +
                "AND second =\n(SELECT user_id FROM users WHERE login = '" + first + "') AND status = 2\n";
        ResultSet result = statement.executeQuery(query);

        return result.next();
    }

    public int update(String query) throws SQLException {
        statement = instance.connection.createStatement();
        return statement.executeUpdate(query);
    }

    public int getUserId(String login) throws SQLException {
        statement = instance.connection.createStatement();
        String query = "SELECT user_id FROM users WHERE login = '" + login + "'";
        ResultSet result = statement.executeQuery(query);
        int id = 0;
        while (result.next()) {
            id = result.getInt(1);
        }

        return id;
    }

    public ArrayList <String> getFriendsList(String login) throws SQLException {
        statement = instance.connection.createStatement();
        int id = getUserId(login);
        String query = "SELECT login FROM friends INNER JOIN users ON users.user_id = friends.second\n" +
                "WHERE first = " + id + " AND status = 2";
        return executeResult(query);

    }

    public ArrayList <String> getSubscribersList(String login) throws SQLException {
        statement = instance.connection.createStatement();
        int id = getUserId(login);
        String query = "SELECT login FROM friends INNER JOIN users ON users.user_id = friends.first\n" +
                "WHERE second = " + id + " AND status = 1";
        return executeResult(query);
    }

    public ArrayList <String> getRequestsList(String login) throws SQLException {
        statement = instance.connection.createStatement();
        int id = getUserId(login);
        String query = "SELECT login FROM friends INNER JOIN users ON users.user_id = friends.second\n" +
                "WHERE first = " + id + " AND status = 1";
        return executeResult(query);
    }

    public ArrayList <String> getMessages(String server, String receiver) throws SQLException {
        statement = instance.connection.createStatement();
        int serverId = getUserId(server);
        int receiverId = getUserId(receiver);
        String query = "SELECT time, login, message FROM messages INNER JOIN users ON users.user_id = " +
                "messages.server WHERE (server = " + serverId + " AND receiver = " + receiverId + ") OR (server = " +
                receiverId + " AND receiver = " + serverId + ")";

        ResultSet result = statement.executeQuery(query);

        ArrayList <String> list = new ArrayList<>();

        while (result.next()) {
            list.add(result.getString(1).trim() + " " +
                    result.getString(2).trim() + " " +
                    result.getString(3).trim());
        }

        if (list.isEmpty()) {
            return null;
        }

        else {
            return list;
        }
    }

    private ArrayList <String> executeResult(String query) throws SQLException {
        ResultSet result = statement.executeQuery(query);

        ArrayList <String> list = new ArrayList<>();

        while (result.next()) {
            list.add(result.getString(1).trim());
        }

        if (list.isEmpty()) {
            return null;
        }

        else {
            return list;
        }
    }
}
