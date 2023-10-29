package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean result = true;
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        loginAndregis lar = new loginAndregis();
        lar = new loginAndregis();

        manage man = new manage();
        man = new manage();

        do {
            try {
                cls.ClearScreen();
                System.out.println("----------------------------");
                System.out.println("    Welcome To ... Shop     ");
                System.out.println("----------------------------");
                System.out.println("1 > Manage For Employee");
                System.out.println("2 > Quit");
                System.out.println("----------------------------");
                System.out.print("\nChoose :> ");
                int Welcomepage = Integer.parseInt(ans.nextLine());
                result = false;
                if (Welcomepage == 1) {

                    lar.EmployeeLogin();
                    man.EmployeeMenu();
                    
                } else if (Welcomepage == 2) {

                    System.exit(0);
                } else {

                    cls.ClearScreen();
                    System.out.println("----------------------------");
                    System.out.println("      Error Try Again       ");
                    System.out.println("----------------------------");
                    Click = ans.nextLine();
                    Main.main(null);
                }

            } catch (Exception e) {

                cls.ClearScreen();
                System.out.println("----------------------------");
                System.out.println("      Error Try Again       ");
                System.out.println("        Only Number         ");
                System.out.println("----------------------------");
                Click = ans.nextLine();
            }
        } while (result);
    }
}