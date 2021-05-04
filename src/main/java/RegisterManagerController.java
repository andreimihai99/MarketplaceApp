import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterManagerController {

    private final NavigatePages nav = new NavigatePages();

    @FXML
    private TextField usernameRegisterField;

    @FXML
    private PasswordField passwordRegisterField;

    @FXML
    private TextField fullnameRegisterField;

    @FXML
    private TextField emailRegisterField;

    @FXML
    private Button registerManagerButton;

    @FXML
    private TextField keyRegisterField;

    @FXML
    void registerManagerAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Marketplace.fxml");
    }

    @FXML
    void registerBackAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/MainMenu.fxml");
    }

}