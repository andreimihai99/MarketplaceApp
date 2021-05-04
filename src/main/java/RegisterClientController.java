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

public class RegisterClientController {

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

    public void addUserToDatabase() {
        JSONObject userInfo = new JSONObject();
        JSONObject userInfoFinal = new JSONObject();
        userInfo.put("username", usernameRegisterField.getText());
        userInfo.put("fullname", fullnameRegisterField.getText());
        userInfo.put("email", emailRegisterField.getText());
        userInfo.put("password", passwordRegisterField.getText());


        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Database/Users.json"));
            JSONArray userArray = (JSONArray) jsonObject.get("Users");

            userArray.add(userInfo);
            try {
                FileWriter file = new FileWriter("src/main/resources/Database/Users.json");
                userInfoFinal.put("Users", userArray);
                file.write(userInfoFinal.toJSONString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int checkRegisterForm() {
        int check = 1;
        if(usernameRegisterField.getText().length() < 6)
            check = 0;
        if(fullnameRegisterField.getText().isEmpty())
            check = 0;
        if(passwordRegisterField.getText().length() < 6)
            check = 0;
        if(!emailRegisterField.getText().contains("@") || emailRegisterField.getText().isEmpty())
            check = 0;
        return check;
    }

    @FXML
    void registerClientAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(checkRegisterForm() == 1) {
            addUserToDatabase();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Thank you for your registration!");
            alert.show();
            nav.changeToPage(event, "src/main/resources/Marketplace.fxml");
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("The form is incomplete or incorrect!");
            alert.show();
        }
    }

    @FXML
    void registerBackAction(ActionEvent event) throws IOException {
        nav.changeToPage(event, "src/main/resources/MainMenu.fxml");
    }

}

