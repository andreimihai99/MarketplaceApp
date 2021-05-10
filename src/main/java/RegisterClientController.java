import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterClientController extends Register{

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
    private Button registerClientButton;

    private final DatabaseAction dbAct = new DatabaseAction();

    private final String secretKey = "ssshhhhhhhhhhh!!!!";

    public void addUserToDatabase() {
        JSONObject userInfo = new JSONObject();

        userInfo.put("username", usernameRegisterField.getText());
        userInfo.put("fullname", fullnameRegisterField.getText());
        userInfo.put("email", emailRegisterField.getText());
        userInfo.put("password", encrypt(passwordRegisterField.getText(), secretKey));
        userInfo.put("role", "client");
        userInfo.put("discount used", "0");


        dbAct.writeElementToDB(userInfo, "Users");
    }

    @FXML
    void registerClientAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(checkRegisterForm(usernameRegisterField.getText(), fullnameRegisterField.getText(), passwordRegisterField.getText(), emailRegisterField.getText()) == 1) {
            addUserToDatabase();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Are you about your credentials? If yes, please login in order to enter the app.");
            alert.show();
            nav.changeToPage(event, "src/main/resources/Scenes/MainMenu.fxml");
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("The form is incomplete or incorrect!");
            alert.show();
        }
    }

    @FXML
    void registerBackAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/Scenes/MainMenu.fxml");
    }

}

