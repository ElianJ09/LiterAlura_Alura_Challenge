package com.alura.com.LiterAlura.Principal;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ________________________________________________________
                                        Select a option
                                        _______________
                            a)  Select a number from the menu (1-9)
                            b)  If you want to exit the program select 0
                    ________________________________________________________
                    
                    1   -   Search a book with the Title 
                    2   -   Registered Books list
                    3   -   Registered Authors list
                    4   -   Living authors List by a certain date (year)
                    5   -   Book List by language
                    
                    _________________________EXTRAS__________________________
                    
                    6   -   Search Author by name
                    7   -   Top 10 books from the API (online)
                    8   -   Top 5 books from the DataBase
                    9   -   Authors of public law                                  
                    0   -   Exit the program
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    System.out.println("Thank you for using LiterAlura! \nExiting program...");
                    break;
                default:
                    System.out.println("Invalid option selected!");
            }
        }
        System.exit(0);
    }
}
