package scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

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
    }
}
