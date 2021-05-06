import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public abstract class Register {

    public int checkRegisterForm(String username, String fullname, String password, String email) {
        int check = 1;
        if(username.length() < 6)
            check = 0;
        if(fullname.isEmpty())
            check = 0;
        if(password.length() < 6)
            check = 0;
        if( email.length() == 0 || (!email.contains("@")))
            check = 0;
        return check;
    }

    public int checkUsernamePassword(String username, String password) {
        JSONParser jsonParser = new JSONParser();
        int correct = 0;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Database/Users.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Users");
            Iterator iterator = jsonArray.iterator();

            while(iterator.hasNext()){
                JSONObject user = (JSONObject) iterator.next();
                if(username.equals(user.get("username")) && password.equals(user.get("password")) && user.get("role").equals("client"))
                    correct = 1;
                else if(username.equals(user.get("username")) && password.equals(user.get("password")) && user.get("role").equals("manager"))
                    correct = 2;
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return correct;
    }

    public int checkLoginForm(String username, String password) {
        int check = 1;
        if(username.isEmpty())
            check = 0;
        if(password.isEmpty())
            check = 0;
        return check;
    }
}
