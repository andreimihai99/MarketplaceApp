import java.util.Scanner;

public class ActionClient {

    public int getActions(){
        Scanner myOption = new Scanner(System.in);
        int option = 0;

        System.out.print("Hello\n" +                                //getting action from user
                "Please introduce your action\n" +
                "1 -> add a product to product list\n" +
                "2 -> delete a product from product list\n" +
                "3 -> modify the price and/or quantity of a product\n" +
                "4 -> display the most expensive product\n" +
                "5 -> display the cheapest product\n" +
                "6 -> enter the marketplace\n" +
                "7 -> show every product in the market\n" +
                "What would you like to do: ");

        option = myOption.nextInt();
        if (option > 7 || option < 1) {
            System.out.println("Please provide a number between 1 and 10\n");
        }
        return option;
    }

    public String moreActions(){
        Scanner myContinue = new Scanner(System.in);
        System.out.print("Any other actions? y(yes)/n(no) ");
        String cont = myContinue.nextLine();
        if(cont.equals("n"))
            System.out.println("Goodbye!");
        else if(!cont.equals("y"))
            System.out.println("Please provide y(yes) or n(no)");
        return cont;
    }

    public int basketActions(){                                 //getting action from user inside the market
        Scanner myOption = new Scanner(System.in);
        int option = 0;

        System.out.print(
                "Please introduce your basket action\n" +
                "1 -> add a product to basket\n" +
                "2 -> delete a product from basket\n" +
                "3 -> show total of basket\n" +
                "4 -> show product list\n" +
                "5 -> show basket\n" +
                "What would you like to do: ");

        option = myOption.nextInt();
        if (option > 5 || option < 1) {
            System.out.println("Please provide a number between 1 and 5\n");
        }
        return option;
    }

    public String moreBasketActions(){
        Scanner myContinue = new Scanner(System.in);
        System.out.print("Any other marketplace actions? y(yes)/n(no) ");
        String cont = myContinue.nextLine();
        if(cont.equals("n"))
            System.out.println("Thank you for buying our products!");
        else if(!cont.equals("y"))
            System.out.println("Please provide y(yes) or n(no)");
        return cont;
    }

}
