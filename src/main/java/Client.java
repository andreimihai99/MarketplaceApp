import java.io.FileNotFoundException;
import java.io.IOException;

public interface Client {

    public void addProduct(String name, String price, String quantity) throws IOException;

    public void deleteProduct(String name, String price, String quantity) throws IOException;

    public void modifyPrice(String name, String newPrice, String newQuantity) throws IOException;

    public void modifyQuantity();

    public void mostExpensiveProduct();

    public void cheapestProduct();

    public void addToBasket(String name, String quantity) throws IOException;

    public void deleteFromBasket(String productName, String productQuantity) throws IOException;

    public int showTotal();
}
