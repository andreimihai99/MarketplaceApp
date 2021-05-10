import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainMenuController extends Register {

    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    private final NavigatePages nav = new NavigatePages();

    @FXML
    void goToMarketplaceButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Scenes/MarketplaceMainClient.fxml");
    }

    @FXML
    void loginButtonAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(checkLoginForm(loginUsernameField.getText(), loginPasswordField.getText()) == 1) {
            if(checkUsernamePassword(loginUsernameField.getText(), loginPasswordField.getText()) == 1)              //if user is a client
                nav.changeToPage(event, "src/main/resources/Scenes/MarketplaceMainClient.fxml");
            else if(checkUsernamePassword(loginUsernameField.getText(), loginPasswordField.getText()) == 1)         //if user is a manager
                nav.changeToPage(event, "src/main/resources/Scenes/MarketplaceMainManager.fxml");
            else {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Username or password is incorrect!");
                alert.show();
            }
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("The form is incomplete or incorrect!");
            alert.show();
        }
    }

    @FXML
    void registerClientButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Scenes/RegisterClient.fxml");
    }

    @FXML
    void registerManagerButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Scenes/RegisterManager.fxml");
    }

}
