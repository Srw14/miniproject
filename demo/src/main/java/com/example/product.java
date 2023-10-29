package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class product extends productdetail {

    private int ProductID = 0;
    private JSONArray list = new JSONArray();
    private JSONArray listCart = new JSONArray();

    String fileproduct = "C:\\Users\\zazaz\\Desktop\\miniproject\\demo\\src\\main\\java\\com\\example\\productinfo.json";
    String filecart = "C:\\Users\\zazaz\\Desktop\\miniproject\\demo\\src\\main\\java\\com\\example\\cart.json";

    public void addproductmenu() {

        boolean result = true;
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        manage man = new manage();
        man = new manage();

        do {
            try {
                cls.ClearScreen();
                System.out.println("----------------------------");
                System.out.println("       Add New Product      ");
                System.out.println("----------------------------");
                System.out.println("1 :> New Product            ");
                System.out.println("2 :> Back to Main Menu      ");
                System.out.println("----------------------------");
                System.out.print("Choose :> ");
                int ProductMenu = Integer.parseInt(ans.nextLine());
                switch (ProductMenu) {
                    case 1:
                        Click = ans.nextLine();
                        addproduct();
                        break;
                    case 2:
                        Click = ans.nextLine();
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

    public void addproduct() {
        boolean result = true;
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        cls.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("        New Product         ");
        System.out.println("----------------------------");
        System.out.print("Input Name :> ");
        this.Name = ans.nextLine();

        // เพิ่มค่า ProductID โดยการอ่านค่า ProductID ล่าสุดจาก JSON และเพิ่ม 1
        this.ProductID = getLastProductID() + 1;

        do {
            try {
                System.out.print("Input Price :> ");
                this.Price = Double.parseDouble(ans.nextLine());
                result = false;

            } catch (Exception e) {
                System.out.println("----------------------------");
                System.out.println("          Only Number       ");
                System.out.println("----------------------------");
            }
        } while (result);

        do {
            try {
                System.out.println("----------------------------");
                System.out.println("1 is drinks || 2 is dessert");
                System.out.println("----------------------------");
                System.out.print("Input Type :> ");
                this.Type = Integer.parseInt(ans.nextLine());
                result = false;

            } catch (Exception e) {
                System.out.println("Only Number");
            }
        } while (result);

        this.Addprotofile();
    }

    private int getLastProductID() {
        // อ่านข้อมูลจาก JSON และหา ProductID ล่าสุด
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileproduct)) {
            JSONArray list = (JSONArray) parser.parse(reader);
            if (!list.isEmpty()) {
                JSONObject lastProduct = (JSONObject) list.get(list.size() - 1);
                return ((Long) lastProduct.get("ProductID")).intValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // ถ้าไม่มีรายการให้ ProductID เริ่มที่ 1
        return 0;
    }

    public void Addprotofile() {
        Scanner ans = new Scanner(System.in);
        String Click = ans.nextLine();
        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        this.Readfromfile();
        JSONObject obj = new JSONObject();
        // เพิ่ม ProductID ลงในข้อมูลผลิตภัณฑ์
        obj.put("ProductID", this.ProductID);
        obj.put("Name", this.Name);
        obj.put("Price", this.Price);
        obj.put("Type", this.Type);

        this.list.add(obj);
        try (FileWriter file = new FileWriter(fileproduct)) {
            file.write(list.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cls.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("            Add             ");
        System.out.println("         New Product        ");
        System.out.println("          Complete          ");
        System.out.println("----------------------------");
        Click = ans.nextLine();
    }

    public void Readfromfile() {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileproduct)) {

            this.list = (JSONArray) parser.parse(reader);

            System.out.println(this.list);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void Productlist() {

        clearscreen cls = new clearscreen();
        cls = new clearscreen();

        cls.ClearScreen();
        System.out.println("-------------------------------------");
        System.out.println("              Product List           ");
        System.out.println("-------------------------------------");

        int i = 0;

        productdetail[] obj = new productdetail[10];
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileproduct)) {

            list = (JSONArray) parser.parse(reader);

            Iterator<JSONObject> iterator = list.iterator(); // loop
            while (iterator.hasNext()) {
                i++;
                JSONObject data = (JSONObject) iterator.next();
                System.out.println("");
                System.out.println("Product ID > " + data.get("ProductID"));
                System.out.println("Name > " + data.get("Name") + "   Price > " + data.get("Price")+"$");
                System.out.println("");
                System.out.println("-------------------------------------");
                // obj[i].setDoProfile(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct() {

        Scanner ans = new Scanner(System.in);
        boolean result = true;
        JSONObject dpro = new JSONObject();

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileproduct)) {

            this.list = (JSONArray) parser.parse(reader);

        } catch (IOException e) {
            // INPUT OUYPUT ERR
            e.printStackTrace();
        } catch (ParseException e) {
            // READ ERR
            e.printStackTrace();
        }

        do {
            try {
                System.out.print("Input Product To Remove : ");
                int delete = Integer.parseInt(ans.nextLine());
                result = false;

                this.list.remove(delete - 1);

                JSONObject pro = new JSONObject();

                try (FileWriter file = new FileWriter(fileproduct)) {
                    file.write(this.list.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("----------------------------");
                System.out.println("Number Only");
                System.out.println("----------------------------");
            }
        } while (result);
    }

    public void viewCart() {

        JSONParser parser = new JSONParser();
        clearscreen cls = new clearscreen();
        cls.ClearScreen();

        System.out.println("----------------------------");
        System.out.println("         Shopping Cart       ");
        System.out.println("----------------------------");

        try (Reader reader = new FileReader(filecart)) {
            listCart = (JSONArray) parser.parse(reader);
            int i = 1;

            for (Object obj : listCart) {
                JSONObject cartItem = (JSONObject) obj;
                System.out.println("Product ID > " + cartItem.get("ProductID"));
                System.out.println("Name: " + cartItem.get("Name"));
                System.out.println("Price: " + cartItem.get("Price")+"$");
                System.out.println("----------------------------");
                i++;
            }

            if (i == 1) {
                System.out.println("Your cart is empty.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void addProductToCart() {
        Scanner ans = new Scanner(System.in);
        System.out.print("Add Product To Cart: ");
        int add = Integer.parseInt(ans.nextLine());
    
        // ตรวจสอบค่า Add
        if (add >= 1 && add <= list.size()) {
            // อ่านข้อมูลปัจจุบันจากไฟล์ cart.json
            JSONParser parser = new JSONParser();
            JSONArray currentCart = new JSONArray();
    
            try (Reader reader = new FileReader(filecart)) {
                currentCart = (JSONArray) parser.parse(reader);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error reading from cart.json.");
                return;
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Error parsing JSON data from cart.json.");
                return;
            }
    
            // อ่านข้อมูลที่ถูกเลือกจาก list
            JSONObject selectedProduct = (JSONObject) list.get(add - 1);
    
            // เพิ่มข้อมูลที่ถูกเลือกลงใน currentCart ถ้าไม่มีอยู่แล้ว
            boolean alreadyInCart = false;
            for (Object obj : currentCart) {
                JSONObject cartItem = (JSONObject) obj;
                if (selectedProduct.equals(cartItem)) {
                    alreadyInCart = true;
                    break;
                }
            }
    
            if (!alreadyInCart) {
                currentCart.add(selectedProduct);
            }
    
            // เขียนข้อมูลในไฟล์ cart.json อีกรอบ
            try (FileWriter file = new FileWriter(filecart)) {
                file.write(currentCart.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing to cart.json.");
                return;
            }
        } else {
            System.out.println("Invalid selection. Please choose a valid product.");
        }
    }
    

    public void deleteProductincart() {

        Scanner ans = new Scanner(System.in);
        boolean result = true;
        JSONObject dpro = new JSONObject();

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(filecart)) {

            this.list = (JSONArray) parser.parse(reader);

        } catch (IOException e) {
            // INPUT OUYPUT ERR
            e.printStackTrace();
        } catch (ParseException e) {
            // READ ERR
            e.printStackTrace();
        }

        do {
            try {
                System.out.print("Input ProductID To Remove : ");
                int delete = Integer.parseInt(ans.nextLine());
                result = false;

                this.list.remove(delete - 1);

                JSONObject pro = new JSONObject();

                try (FileWriter file = new FileWriter(filecart)) {
                    file.write(this.list.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("----------------------------");
                System.out.println("Number Only");
                System.out.println("----------------------------");
            }
        } while (result);
    }

    public void clearCart() {
    listCart.clear(); 
    try (FileWriter file = new FileWriter(filecart)) {
        file.write(listCart.toJSONString());
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error writing to file.");
    }
    System.out.println("Cart has been cleared.");
}

}
