import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MarketplaceMainClientController {

    @FXML
    private TextField searchProductField;

    @FXML
    private TextField productQuantityField;

    @FXML
    private Label availabilityLabel;

    private NavigatePages nav = new NavigatePages();

    @FXML
    void addToBasketButtonAction(ActionEvent event) {

    }

    @FXML
    void goToRegisterButtonAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Scenes/RegisterClient.fxml");
    }

    @FXML
    void seeProductsButtonAction(ActionEvent event) {

    }

    @FXML
    void checkAvailabilityButtonAction(ActionEvent event) {

    }

}