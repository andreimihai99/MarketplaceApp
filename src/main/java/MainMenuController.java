import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    private final NavigatePages nav = new NavigatePages();

    @FXML
    void goToMarketplaceButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Marketplace.fxml");
    }

    @FXML
    void loginButtonAction(ActionEvent event) {

    }

    @FXML
    void registerClientButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/RegisterClient.fxml");
    }

    @FXML
    void registerManagerButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/RegisterManager.fxml");
    }

}
