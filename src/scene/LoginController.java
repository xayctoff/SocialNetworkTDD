package scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            closeLoginWindow(signInButton);
            saveUser(name, password);
            openMainWindow(name);
        }

        else {
            showMessage("Неправильный логин или пароль");
        }
    }

    public void signUp() throws Exception {
        String name = loginField.getText();
        String password = passwordField.getText();

        if (login.signUp(name, password)) {
            closeLoginWindow(signUpButton);
            saveUser(name, password);
            openMainWindow(name);
        }

        else {
            showMessage("Пользователь с таким именем уже существует");
        }

    }

    private void closeLoginWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private void saveUser(String login, String password) {
        user = new User();
        user.setLogin(login);
        user.setPassword(password);
    }

    private void openMainWindow(String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка авторизации");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
