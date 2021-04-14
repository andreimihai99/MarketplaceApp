import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DefaultClient implements Client{

    ActionClient ac = new ActionClient();

    public void doAction() throws IOException {
        do{
            emptyBasket();
            int n = ac.getActions();
            if(n == 1){                                            //for some reason the switch case wasn't working properly, so I implemented the methods with multiple ifs
                Scanner nameOption = new Scanner(System.in);
                System.out.println("Name of product: ");
                String name = nameOption.nextLine();

                Scanner priceOption = new Scanner(System.in);
                System.out.println("Price of product: ");
                String price = priceOption.nextLine();

                Scanner qOption = new Scanner(System.in);
                System.out.println("Quantity of product: ");
                String quantity = qOption.nextLine();

                addProduct(name, price, quantity);

            } else if(n == 2){
                Scanner delNameOption = new Scanner(System.in);
                System.out.println("Name of product to delete: ");
                String nameDel = delNameOption.nextLine();

                Scanner delPriceOption = new Scanner(System.in);
                System.out.println("Price of product to delete: ");
                String priceDel = delPriceOption.nextLine();

                Scanner delQtOption = new Scanner(System.in);
                System.out.println("Quantity of product to delete: ");
                String quantityDel = delQtOption.nextLine();

                deleteProduct(nameDel, priceDel, quantityDel);

            } else if(n == 3){
                Scanner replNameOption = new Scanner(System.in);
                System.out.println("Name of product to modify price: ");
                String nameRepl = replNameOption.nextLine();

                Scanner replPriceOption = new Scanner(System.in);
                System.out.println("New price for product: ");
                String priceRepl = replPriceOption.nextLine();

                Scanner replQOption = new Scanner(System.in);
                System.out.println("New quantity for product: ");
                String qRepl = replQOption.nextLine();


                modifyPrice(nameRepl, priceRepl, qRepl);

            } else if(n == 4){
                modifyQuantity();

            } else if(n == 5){
                mostExpensiveProduct();

            } else if(n == 6){
                cheapestProduct();

            } else if(n == 7){
                do{
                    int b = ac.basketActions();

                    if(b == 1){
                        Scanner addBasketNameOption = new Scanner(System.in);
                        System.out.println("Name of the product: ");
                        String nameAddBasket = addBasketNameOption.nextLine();

                        Scanner addBasketQuantityOption = new Scanner(System.in);
                        System.out.println("Quantity of the product: ");
                        String quantityAddBasket = addBasketQuantityOption.nextLine();

                        addToBasket(nameAddBasket, quantityAddBasket);

                    } else if(b == 2){
                        Scanner delBasketNameOption = new Scanner(System.in);
                        System.out.println("Name of the product you want to delete from basket: ");
                        String nameDelBasket = delBasketNameOption.nextLine();

                        Scanner delBasketQuantityOption = new Scanner(System.in);
                        System.out.println("Quantity of the product you want to delete from basket: ");
                        String quantityDelBasket = delBasketQuantityOption.nextLine();

                        addToBasket(nameDelBasket, quantityDelBasket);

                    } else if(b == 3){

                    } else if(b == 4){
                        printProducts();
                    }

                }while(ac.moreBasketActions().equals("y"));

            } else if(n == 8) {
                printProducts();
            }
        }while(ac.moreActions().equals("y"));
    }


    @Override
    public void addProduct(String name, String price, String quantity) throws IOException {
        String newProduct = name + ":" + price + ":" + quantity;

        try{
            FileWriter fw = new FileWriter("documents/store.txt", true);
            fw.write(newProduct + "\n");
            fw.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(String name, String price, String quantity) throws IOException {
        String delProduct = name + ":" + price + ":" + quantity;

        File inputFile = new File("documents/store.txt");
        File tempFile = new File("documents/newstore.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(delProduct)) continue;
            writer.write(currentLine + "\n");           //writing from the input file to the auxiliary file
        }

        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void printProducts(){
        String line = "";
        String splitBy = ":";
        String[] product = new String[100];
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("documents/store.txt"));
            while((line = br.readLine()) != null) {
                String[] products = line.split(splitBy);

                product[i] = "Product " + products[0] + ", Price " + products[1] + ", Quantity " + products[2];
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int c = 0; c < i; c++){
            System.out.println(product[c]);
        }
    }

    @Override
    public void modifyPrice(String newProductName, String newPrice, String newProductQuantity) throws IOException {
        String line = "";
        String splitBy = ":";
        String[] product = new String[100];
        String[] products = new String[4];
        String oldPrice = "", oldName = "", oldQuantity = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("documents/store.txt"));
            while((line = br.readLine()) != null) {
                products = line.split(splitBy);
                if(products[0].equals(newProductName)) {
                    oldName = products[0];
                    oldPrice = products[1];
                    oldQuantity = products[2];
                    newProductQuantity = products[2];
                    break;
                }
            }
            deleteProduct(oldName, oldPrice, oldQuantity);

        } catch (IOException e) {
            e.printStackTrace();
        }
        addProduct(newProductName, newPrice, newProductQuantity);
        deleteProduct(oldName, oldPrice, oldQuantity);

    }

    @Override
    public void modifyQuantity() {
        System.out.println("4");
    }

    @Override
    public void mostExpensiveProduct() {
        String line = "";
        String splitBy = ":";
        String maxProduct = "";
        String[] products;
        int max = Integer.MIN_VALUE;
        try{
            BufferedReader br = new BufferedReader(new FileReader("documents/store.txt"));
             while ((line = br.readLine()) != null) {
                products = line.split(splitBy);
                if (Integer.parseInt(products[1]) > max) {
                    maxProduct = "Product " + products[0] + ", Price " + products[1];
                    max = Integer.parseInt(products[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(maxProduct);
    }

    @Override
    public void cheapestProduct() {
        String line = "";
        String splitBy = ":";
        String minProduct = "";
        String[] products;
        int min = Integer.MAX_VALUE;
        try{
            BufferedReader br = new BufferedReader(new FileReader("documents/store.txt"));
            while ((line = br.readLine()) != null) {
                products = line.split(splitBy);
                if (Integer.parseInt(products[1]) < min) {
                    minProduct = "Product " + products[0] + ", Price " + products[1];
                    min = Integer.parseInt(products[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(minProduct);
    }

    @Override
    public void addToBasket(String productName, String productQuantity) throws IOException {
        String newProduct = productName + ":" + productQuantity;

        try{
            FileWriter fw = new FileWriter("documents/basket.txt", true);
            fw.write(newProduct + "\n");
            fw.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteFromBasket(String productName, String productQuantity) throws IOException {
        String delProduct = productName + ":" + productQuantity;

        File inputFile = new File("documents/basket.txt");
        File tempFile = new File("documents/newbasket.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(delProduct)) continue;
            writer.write(currentLine + "\n");           //writing from the input file to the auxiliary file
        }

        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void emptyBasket() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("documents/basket.txt");
        writer.print("");
        writer.close();
    }

    @Override
    public int showTotal() {
        return 0;
    }
}
