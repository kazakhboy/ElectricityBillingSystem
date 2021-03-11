package com.company;
import java.sql.SQLException;
import java.util.Scanner;
public class Driver {
    public void start() throws SQLException {
        ElectricityBilling eb = new ElectricityBilling();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do? Choose one of the options:");
            System.out.println("1. Calculate the total energy consumption");
            System.out.println("2. See energy consumption of different electrical techniques:");
            System.out.println("3. Calculate the total power of household techniques: ");
            System.out.println("4. Exit program");
            int input = s.nextInt();
            if (input == 1) {
                eb.firstChoice();
                continue;
            }
            if (input == 2){
                eb.secondChoice();
                continue;
            }
            if (input == 3){
                eb.thirdChoice();
                continue;
            }
            else {
                eb.fourthChoice();
                break;
            }
        }
    }
}
