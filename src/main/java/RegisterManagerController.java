import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class RegisterManagerController extends Register {

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

    private final DatabaseAction dbAct = new DatabaseAction();
    private final String secretKey = "ssshhhhhhhhhhh!!!!";

    @FXML
    void registerManagerAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(checkRegisterForm(usernameRegisterField.getText(), fullnameRegisterField.getText(), passwordRegisterField.getText(), emailRegisterField.getText()) == 1) {
            addManagerToDatabase();
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
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

    public void addManagerToDatabase() {
        JSONObject userInfo = new JSONObject();

        userInfo.put("username", usernameRegisterField.getText());
        userInfo.put("fullname", fullnameRegisterField.getText());
        userInfo.put("email", emailRegisterField.getText());
        userInfo.put("password", encrypt(passwordRegisterField.getText(), secretKey));
        userInfo.put("role", "manager");

        dbAct.writeElementToDB(userInfo, "Users", "src/main/resources/Database/Users.json");
    }

}