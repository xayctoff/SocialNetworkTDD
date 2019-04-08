package scene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Database;
import model.User;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPageController {

    private User user;

    private Database database;
    {
        try {
            database = Database.getInstance();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button acceptButton;

    @FXML
    private Button declineButton;

    @FXML
    private Button addButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button sendButton;

    @FXML
    private ListView <String> friendsList = new ListView<>();

    @FXML
    private ListView <String> subscribersList = new ListView<>();

    @FXML
    private ListView <String> requestsList = new ListView<>();

    @FXML
    private ListView <String> searchResult = new ListView<>();

    @FXML
    private ListView <String> dialog;

    @FXML
    private TextField searchField;

    @FXML
    private TextArea messageField;

    @FXML
    private Label loginLabel;

    @FXML
    private Label receiverLabel;

    @FXML
    public void acceptRequest() throws Exception {
        String subscriber = subscribersList.getSelectionModel().getSelectedItems().toString();
        user.confirmFriendship(user.getLogin(), subscriber, true);
        fillFriendsList();
        fillSubscribersList();
    }

    @FXML
    public void declineRequest() throws Exception {
        String subscriber = subscribersList.getSelectionModel().getSelectedItems().toString();
        user.confirmFriendship(user.getLogin(), subscriber, false);
        fillFriendsList();
        fillSubscribersList();
    }

    @FXML
    public void searchPeople() throws SQLException {
        ArrayList<String> result = new ArrayList<>(database.searchPeople(searchField.getText()));
        ObservableList <String> observableList = FXCollections.observableArrayList(result);
        dialog.getItems().addAll(observableList);
    }

    @FXML
    public void addFriend() throws SQLException {
        if (user.addFriend(user.getLogin(), searchResult.getSelectionModel().getSelectedItems().toString())) {
            fillRequestsList();
        }

        else {
            showMessage();
        }
    }

    @FXML
    public void sendMessage() throws SQLException {
        if (!messageField.getText().equals("")) {
            user.writeMessage(user.getLogin(), receiverLabel.getText(), messageField.getText());
            fillMessagesList();
        }
    }

    @FXML
    public void signOut() {
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.close();
    }

    public void setLoginLabel() {
        loginLabel.setText(user.getLogin());
    }

    public void setReceiverLabel() throws SQLException {
        receiverLabel.setText(chooseUser());
        fillMessagesList();
    }

    private String chooseUser() {
        return friendsList.getSelectionModel().getSelectedItem();
    }

    public void fillFriendsList() throws SQLException {
        friendsList.getItems().clear();
        ArrayList <String> friends = database.getFriendsList(user.getLogin());

        if (friends != null) {
            ObservableList<String> observableList = FXCollections.observableArrayList(friends);
            friendsList.setItems(observableList);
        }
    }

    public void fillSubscribersList() throws SQLException {
        subscribersList.getItems().clear();
        ArrayList <String> subscribers = database.getSubscribersList(user.getLogin());

        if (subscribers != null) {
            ObservableList<String> observableList = FXCollections.observableArrayList(subscribers);
            subscribersList.setItems(observableList);
        }
    }

    public void fillRequestsList() throws SQLException {
        requestsList.getItems().clear();
        ArrayList <String> requests = database.getRequestsList(user.getLogin());

        if (requests != null) {
            ObservableList<String> observableList = FXCollections.observableArrayList(requests);
            requestsList.setItems(observableList);
        }
    }

    public void fillMessagesList() throws SQLException {
        dialog.getItems().clear();
        ArrayList <String> messages = database.getMessages(user.getLogin(), receiverLabel.getText());

        if (messages != null) {
            ObservableList<String> observableList = FXCollections.observableArrayList(messages);
            dialog.setItems(observableList);
        }
    }

    private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка авторизации");
        alert.setContentText("Пользователь с таким именем уже в друзьях");
        alert.showAndWait();
    }

    public void setUser(User user) {
        this.user = user;
    }

}
