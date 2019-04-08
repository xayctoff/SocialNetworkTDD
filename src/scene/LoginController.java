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
            saveUser(name, password);
            openMainWindow(name);
            closeLoginWindow(signInButton);
        }

        else {
            showMessage("Неправильный логин или пароль");
        }
    }

    @FXML
    public void signUp() throws Exception {
        String name = loginField.getText();
        String password = passwordField.getText();

        if (login.signUp(name, password)) {
            saveUser(name, password);
            openMainWindow(name);
            closeLoginWindow(signUpButton);
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

    @FXML
    private void openMainWindow(String title) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainpage.fxml"));
            Parent root = loader.load();
            MainPageController controller = loader.getController();
            controller.setUser(user);
            controller.setLoginLabel();
            controller.fillFriendsList();
            controller.fillSubscribersList();
            controller.fillRequestsList();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }

        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка авторизации");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        signInButton.setOnAction(event -> {
            try {
                signIn();
            }

            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        signUpButton.setOnAction(event -> {
            try {
                signUp();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
