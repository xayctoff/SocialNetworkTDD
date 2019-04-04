package scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Login;
import model.User;

public class LoginController {

    private User user;

    private Login login = new Login();

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void signIn() throws Exception {
        String name = loginField.getText();
        String password = passwordField.getText();

        if (login.signIn(name, password)) {
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.close();

            user = new User();
            user.setLogin(name);
            user.setPassword(password);

            openMainWindow(name);
        }

        else {
            showMessage();
        }
    }

    private void openMainWindow(String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

}
