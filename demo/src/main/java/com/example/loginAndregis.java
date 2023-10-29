package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class loginAndregis extends Employee {

    private boolean result = true;
    private JSONArray list = new JSONArray();

    String fileadmin = "C:\\Users\\zazaz\\Desktop\\miniproject\\demo\\src\\main\\java\\com\\example\\Employeeinfo.json";

    public void EmployeeLogin() {

        Scanner ans = new Scanner(System.in);
        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        cls.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("     Please Login First     ");
        System.out.println("----------------------------");
        System.out.print("  Username :> ");
        String user = ans.nextLine();
        System.out.print("  Password :> ");
        String pass = ans.nextLine();

        int i = 0;
        int EmployeeUser = 0, EmployeePass = 0;
        JSONArray list = new JSONArray();
        JSONParser parser = new JSONParser();
        loginAndregis[] login = new loginAndregis[100];
        String rPass = "";

        try (Reader reader = new FileReader(fileadmin)) {

            list = (JSONArray) parser.parse(reader);

            Iterator<JSONObject> iterator = list.iterator(); // loop
            while (iterator.hasNext()) {

                i++;
                JSONObject data = (JSONObject) iterator.next();

                this.Username = (String) data.get("Username");
                this.Password = (String) data.get("Password");

                if (user.equals(this.Username)) {
                    EmployeeUser = 1;
                    rPass = this.Password;
                }

                if (pass.equals(rPass)) {
                    EmployeePass = 1;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (EmployeeUser == 1 && EmployeePass == 1) {
            cls.ClearScreen();
            System.out.println("----------------------------");
            System.out.println("           Welcome          ");
            System.out.println("----------------------------");
            System.out.println("       LOGIN COMPLETE       ");
            System.out.println("----------------------------");

        } else if (EmployeeUser == 0) {
            cls.ClearScreen();
            System.out.println("----------------------------");
            System.out.println("            Sorry           ");
            System.out.println("----------------------------");
            System.out.println(" YOUR USERNAME IS INCORRECT ");
            System.out.println("----------------------------");
            Main.main(null);

        } else if (EmployeePass == 0) {
            cls.ClearScreen();
            System.out.println("----------------------------");
            System.out.println("            Sorry           ");
            System.out.println("----------------------------");
            System.out.println(" YOUR PASSWORD IS INCORRECT ");
            System.out.println("----------------------------");
            Main.main(null);

        }
    }

    public void AddEmp() {
        Scanner ans = new Scanner(System.in);
        clearscreen cls = new clearscreen();
        cls = new clearscreen();
    
        cls.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("          Register          ");
        System.out.println("        New Employee        ");
        System.out.println("----------------------------");
        System.out.print("Input Firstname :> ");
        this.Firstname = ans.nextLine();
    
        System.out.print("Input Lastname :> ");
        this.Lastname = ans.nextLine();
    
        System.out.print("Input Username :> ");
        this.Username = ans.nextLine();
    
        System.out.print("Input Password :> ");
        this.Password = ans.nextLine();
    
        do {
            try {
                System.out.print("Input Age :> ");
                this.Age = Integer.parseInt(ans.nextLine());
                result = false;
    
            } catch (Exception e) {
                System.out.println("Only Number");
            }
        } while (result);
    
        this.Addemptofile();
    }

    public void Addemptofile() {
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();
        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        this.Readfromfile();
        JSONObject obj = new JSONObject();
        obj.put("Firstname", this.Firstname);
        obj.put("Lastname", this.Lastname);
        obj.put("Age", this.Age);
        obj.put("Username", this.Username);
        obj.put("Password", this.Password);

        this.list.add(obj);
        try (FileWriter file = new FileWriter(fileadmin)) {
            file.write(list.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cls.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("            Add             ");
        System.out.println("        New Employee        ");
        System.out.println("          Complete          ");
        System.out.println("----------------------------");
        Click = ans.nextLine();
    }

    public void Readfromfile() {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileadmin)) {

            this.list = (JSONArray) parser.parse(reader);

            System.out.println(this.list);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void Employeelist() {

        
        clearscreen cls = new clearscreen();
        cls = new clearscreen();
    
        cls.ClearScreen();
        System.out.println("-------------------------------------");
        System.out.println("             Employee List           ");
        System.out.println("-------------------------------------");
    
        int i = 0;
    
        Employee[] obj = new Employee[10];
        JSONParser parser = new JSONParser();
    
        try (Reader reader = new FileReader(fileadmin)) {
    
            list = (JSONArray) parser.parse(reader);
    
            Iterator<JSONObject> iterator = list.iterator(); // loop
            while (iterator.hasNext()) {
                i++;
                JSONObject data = (JSONObject) iterator.next();
                System.out.println("");
                System.out.println("Employee ID > " + i + "   Username > " + data.get("Username"));
                System.out.println("Name > " + data.get("Firstname") + "   " + data.get("Lastname"));
                System.out.println("");
                System.out.println("-------------------------------------");    
                obj[i] = new Employee();
                // obj[i].setDoProfile(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmp() {

        Scanner ans = new Scanner(System.in);
        boolean result = true;
        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        JSONObject dobj = new JSONObject();

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileadmin)) {

            this.list = (JSONArray) parser.parse(reader);

        } catch (IOException e) {
            // INPUT OUYPUT ERR
            e.printStackTrace();
        } catch (ParseException e) {
            // READ ERR
            e.printStackTrace();
        }

        do{
            try{
                System.out.print("Input Employee ID To Remove : ");
                int delete = Integer.parseInt(ans.nextLine());
                result = false;
        
                this.list.remove(delete - 1);
        
                JSONObject Obj = new JSONObject();
        
                try (FileWriter file = new FileWriter(fileadmin)) {
                    file.write(this.list.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }catch (Exception e) {
                System.out.println("----------------------------");
                System.out.println("Number Only");
                System.out.println("----------------------------");
            }
        }while (result);
    }
    
}
