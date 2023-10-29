package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class payment {

    double sum = 0;
    private JSONArray listCart = new JSONArray();
    String filecart = "C:\\Users\\zazaz\\Desktop\\miniproject\\demo\\src\\main\\java\\com\\example\\cart.json";

    public void paymentmanu() {

        boolean result = true;
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();

        manage man = new manage();
        man = new manage();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        product pro = new product();
        pro = new product();

        do {
            try {

                System.out.println("           Payment          ");
                System.out.println("----------------------------");
                System.out.println("1 :> Make Payment           ");
                System.out.println("2 :> Add Product To Cart    ");
                System.out.println("3 :> clear Cart             ");
                System.out.println("4 :> Back                   ");
                System.out.println("----------------------------");
                System.out.print("Choose :> ");
                int pay = Integer.parseInt(ans.nextLine());
                switch (pay) {
                    case 1:
                        cls.ClearScreen();
                        makepayment();
                        Click = ans.nextLine();
                        break;
                    case 2:
                        cls.ClearScreen();
                        pro.Productlist();
                        pro.addProductToCart();
                        Click = ans.nextLine();
                        break;
                    case 3:
                        cls.ClearScreen();
                        pro.clearCart();
                        break;
                    case 4:
                        cls.ClearScreen();
                        man.EmployeeMenu();
                        break;
                }
            } catch (Exception e) {

                cls.ClearScreen();
                System.out.println("----------------------------");
                System.out.println("      Error Try Again       ");
                System.out.println("        Only Number         ");
                System.out.println("----------------------------");
            }
        } while (result);

    }

    public void makepayment() {

        JSONParser parser = new JSONParser();
        boolean result = true;
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        cls.ClearScreen();

        System.out.println("----------------------------");
        System.out.println("            Payment         ");
        System.out.println("----------------------------");

        try (Reader reader = new FileReader(filecart)) {
            listCart = (JSONArray) parser.parse(reader);
            int i = 1;

            for (Object obj : listCart) {
                JSONObject cartItem = (JSONObject) obj;
                System.out.println("Name: " + cartItem.get("Name"));
                System.out.println("Price: " + cartItem.get("Price") + "$");
                System.out.println("----------------------------");
                i++;
            }

            if (i == 1) {
                System.out.println("Your cart is empty.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        Sum();

    }

    public void Sum() {
        clearscreen cls = new clearscreen();
        cls = new clearscreen();
        manage man = new manage();
        man = new manage();

        cls.ClearScreen();

        JSONParser parser = new JSONParser();

        try {
            Object obj1 = parser.parse(new FileReader(filecart));
            JSONArray jsonArray1 = (JSONArray) obj1;

            sum = 0.0;

            for (Object o : jsonArray1) {
                JSONObject jsonObject = (JSONObject) o;
                Object priceObject = jsonObject.get("Price");
                if (priceObject instanceof Number) {
                    double price = ((Number) priceObject).doubleValue();
                    sum += price;
                }
            }

            System.out.println("----------------------------");
            System.out.println("Total Price: " + sum + "$");
            System.out.println("----------------------------");
            System.out.println("Did you Confirm");
            System.out.println("1 :> YES || 2 :> NO");
            System.out.println("----------------------------");

            boolean result = true;
            Scanner ans = new Scanner(System.in);

            do {
                try {
                    System.out.print("Choose :> ");
                    int ans1 = Integer.parseInt(ans.nextLine());
                    result = false;

                    if (ans1 == 1) {
                        Confirmpayment();
                    } else if (ans1 == 2) {
                        cls.ClearScreen();
                        man.EmployeeMenu();
                    } else {
                        Sum();
                    }
                } catch (Exception e) {
                    System.out.println("----------------------------");
                    System.out.println("Error Try Again");
                    System.out.println("Number Only");
                    System.out.println("----------------------------");
                }
            } while (result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Confirmpayment() {

        product pro = new product();
        pro = new product();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();
        
        manage man = new manage();
        man = new manage();

        cls.ClearScreen();

        boolean result = true;
        Scanner ans = new Scanner(System.in);

        do {
            try {
                System.out.println("----------------------------");
                System.out.print("Total Price: " + sum + "\n");
                System.out.print("Enter the amount received: ");
                double amountReceived = Double.parseDouble(ans.nextLine());

                if (amountReceived >= sum) {
                    double change = amountReceived - sum;
                    System.out.println("Change: " + change);

                    // Reset the sum and update the cart after successful payment
                    sum = 0.0;
                    listCart.clear();

                    System.out.println("Thank you for your payment!");
                    System.out.println("----------------------------");
                    System.out.println("Press any key to continue...");
                    pro.clearCart();
                    ans.nextLine();
                    man.EmployeeMenu();
                } else {
                    System.out.println("Insufficient amount. Please enter a sufficient amount.");
                    Confirmpayment();
                }

                result = false;
            } catch (Exception e) {
                System.out.println("----------------------------");
                System.out.println("Error Try Again");
                System.out.println("Number Only");
                System.out.println("----------------------------");
            }
        } while (result);
    }
    

}
