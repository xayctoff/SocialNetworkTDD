package scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Login;
import model.User;

public class MainPageController {

    private User user;

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

}
