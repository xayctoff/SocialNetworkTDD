package scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Database;
import model.User;

import java.net.URL;
import java.sql.SQLException;
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
    private ListView friendsList;

    @FXML
    private ListView subscribersList;

    @FXML
    private ListView searchResult;

    @FXML
    private ListView dialog;

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
        database.getFriendsList(user.getLogin());
        database.getSubscribersList(user.getLogin());
    }

    @FXML
    public void declineRequest() throws Exception {
        String subscriber = subscribersList.getSelectionModel().getSelectedItems().toString();
        user.confirmFriendship(user.getLogin(), subscriber, false);
        database.getFriendsList(user.getLogin());
        database.getSubscribersList(user.getLogin());
    }

    private void setLoginLabel() {
        loginLabel.setText(user.getLogin());
    }

    private void getUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        LoginController controller = loader.getController();
        this.user = controller.getUser();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
        setLoginLabel();

        try {
            database.getFriendsList(user.getLogin());
            database.getSubscribersList(user.getLogin());
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
