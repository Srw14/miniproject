package com.example;

import java.util.Scanner;

public class manage {
    public static void EmployeeMenu() {

        boolean result = true;
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        loginAndregis lar = new loginAndregis();
        lar = new loginAndregis();

        product pro = new product();
        pro = new product();

        payment pay = new payment();
        pay = new payment();

        do {
            try {
                cls.ClearScreen();
                System.out.println("----------------------------");
                System.out.println("     Manage For Employee    ");
                System.out.println("----------------------------");
                System.out.println("1 :> Employee List          ");
                System.out.println("2 :> Delete Employee        ");
                System.out.println("3 :> Add New Employee       ");
                System.out.println("4 :> Add Product            ");
                System.out.println("5 :> Product List           ");
                System.out.println("6 :> Product Cart           ");
                System.out.println("7 :> Delete Product Form List");
                System.out.println("8 :> Delete Product Form Cart");
                System.out.println("9 :> Quit                   ");
                System.out.println("----------------------------");
                System.out.print("Choose :> ");
                int EmployeeMenu = Integer.parseInt(ans.nextLine());
                switch (EmployeeMenu) {
                    case 1:
                        cls.ClearScreen();
                        lar.Employeelist();
                        Click = ans.nextLine();
                        EmployeeMenu();
                        break;
                    case 2:
                        cls.ClearScreen();
                        lar.Employeelist();
                        lar.deleteEmp();
                        Click = ans.nextLine();
                        EmployeeMenu();
                        break;
                    case 3:
                        cls.ClearScreen();
                        lar.AddEmp();
                        Click = ans.nextLine();
                        break;
                    case 4:
                        cls.ClearScreen();
                        pro.addproductmenu();
                        Click = ans.nextLine();
                        break;
                    case 5:
                        cls.ClearScreen();
                        pro.Productlist();
                        pro.addProductToCart();
                        Click = ans.nextLine();
                        break;
                    case 6:
                        cls.ClearScreen();
                        pro.viewCart();
                        pay.paymentmanu();
                        Click = ans.nextLine();
                        break;
                    case 7:
                        cls.ClearScreen();
                        pro.Productlist();
                        pro.deleteProduct();
                        Click = ans.nextLine();
                        break;
                    case 8:
                        cls.ClearScreen();
                        pro.viewCart();
                        pro.deleteProductincart();
                        Click = ans.nextLine();
                        break;
                    case 9:
                        cls.ClearScreen();
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
}
