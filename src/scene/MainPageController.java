package scene;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Database;
import model.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private User user;

    private Database database = Database.getInstance();

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
    private ListView <String> friendsList;

    @FXML
    private ListView <String> subscribersList;

    @FXML
    private ListView <String> searchResult;

    @FXML
    private ListView <String> dialog;

    @FXML
    private TextField searchField;

    @FXML
    private TextArea messageField;

    @FXML
    private Label loginLabel;

    public MainPageController() throws SQLException {}

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
        ArrayList <String> result = database.searchPeople(searchField.getText());
        ObservableList <String> observableList = FXCollections.observableArrayList(result);
        friendsList.getItems().addAll(observableList);
    }




    private void setLoginLabel() {
        loginLabel.setText(user.getLogin());
    }

    private void getUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        LoginController controller = loader.getController();
        this.user = controller.getUser();
    }

    private void fillFriendsList() throws SQLException {
        ArrayList <String> friends = database.getFriendsList(user.getLogin());
        ObservableList <String> observableList = FXCollections.observableArrayList(friends);
        friendsList.getItems().addAll(observableList);
    }

    private void fillSubscribersList() throws SQLException {
        ArrayList <String> subscribers = database.getSubscribersList(user.getLogin());
        ObservableList <String> observableList = FXCollections.observableArrayList(subscribers);
        friendsList.getItems().addAll(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
        setLoginLabel();

        try {
            fillFriendsList();
            fillSubscribersList();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
