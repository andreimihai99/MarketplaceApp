import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseAction {

    public void writeElementToDB(JSONObject obj, String dbName) {              //writes the desired JSONObject in it's respective database
        JSONObject userInfoFinal = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Database/Users.json"));
            JSONArray userArray = (JSONArray) jsonObject.get(dbName);

            userArray.add(obj);
            try {
                FileWriter file = new FileWriter("src/main/resources/Database/Users.json");
                userInfoFinal.put(dbName, userArray);
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


}
