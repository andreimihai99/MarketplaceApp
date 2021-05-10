import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;

public abstract class Register {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private final String secret = "ssshhhhhhhhhhh!!!!";

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

    public int checkUsernamePassword(String username, String password) {            //parses through the json and checks the validity of the info provided by the user
        JSONParser jsonParser = new JSONParser();
        int correct = 0;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Database/Users.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Users");
            Iterator iterator = jsonArray.iterator();

            while(iterator.hasNext()){
                JSONObject user = (JSONObject) iterator.next();
                if(username.equals(user.get("username")) && encrypt(password, secret).equals(user.get("password")) && user.get("role").equals("client"))        //for client
                    correct = 1;
                else if(username.equals(user.get("username")) && encrypt(password, secret).equals(user.get("password")) && user.get("role").equals("manager"))      //for manager
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

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}
