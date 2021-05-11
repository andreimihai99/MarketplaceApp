import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class MarketplaceMainManagerController {

    @FXML
    private TextField newProdNameField;

    @FXML
    private TextField newProdQuantityField;

    @FXML
    private TextField newProdPriceField;

    @FXML
    private TextField deleteProdName;

    private final DatabaseAction dbAct = new DatabaseAction();


    @FXML
    void addNewProductButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(!newProdNameField.getText().isEmpty()) {
            createProductElement();
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("You must introduce the product name!");
            alert.show();
        }
    }

    public void createProductElement() {
        Product product = new Product(newProdNameField.getText(), newProdQuantityField.getText(), newProdPriceField.getText());
        JSONObject productInfo = new JSONObject();

        productInfo.put("name", product.getName());
        productInfo.put("quantity", product.getQuantity());
        productInfo.put("price", product.getPrice());

        dbAct.writeElementToDB(productInfo, "Products", "src/main/resources/Database/Products.json");

    }

    @FXML
    void deleteProductButtonAction(ActionEvent event) {

    }

    @FXML
    void displayProductsButtonAction(ActionEvent event) {

    }

    @FXML
    void modifyProductButtonAction(ActionEvent event) {

    }

}
