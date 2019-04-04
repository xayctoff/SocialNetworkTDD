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
        ResultSet result = statement.executeQuery(query);

        ArrayList <String> people = new ArrayList<>();

        while (result.next()) {
            people.add(result.getString("login"));
        }

        if (people.isEmpty()) {
            return null;
        }

        else {
            return people;
        }

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

    public int getUserId(String query) throws SQLException {
        statement = instance.connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        int id = 0;
        while (result.next()) {
            id = result.getInt(0);
        }

        return id;
    }
}
