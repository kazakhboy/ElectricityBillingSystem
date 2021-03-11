package com.company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ElectricityBilling {
    Database database = new Database();
    public void firstChoice() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("How many electrical techniques you want to add?");
        int number = s.nextInt();
        ArrayList<String> techniques = new ArrayList<>();
        ArrayList<Integer> technique_consumption = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            System.out.println("Please type the name of the electrical technique: ");
            String tempTechnique = s.next();
            techniques.add(tempTechnique);
        }
        ResultSet rs = null;
        String query = "SELECT technique_name, technique_consumption FROM techniques WHERE technique_name = ?";
        for(int i=0; i<number; i++){
            try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
                ps.setString(1, techniques.get(i));
                rs = ps.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rs.next();
            technique_consumption.add(rs.getInt("technique_consumption"));
        }
        for (int i = 0; i < number; i++) {
            System.out.println("Technique " + techniques.get(i) + " consumes " + technique_consumption.get(i) + "W energy!");
        }
        System.out.println("\n");
    }
    public void secondChoice() throws SQLException {
        Statement stmt = database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM techniques");
        while (rs.next()) {
            String technique_name = rs.getString("technique_name");
            int technique_consumption = rs.getInt("technique_consumption");
            System.out.println(technique_name + " consumes " + technique_consumption + "W energy!");
        }
        System.out.println("\n");
    }
    public void thirdChoice() {
        Scanner s = new Scanner(System.in);
        System.out.println("How many electrical techniques do you have in your house?");
        int number = s.nextInt();
        ArrayList<String> techniques = new ArrayList<>();
        ArrayList<Double> technique_power = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            System.out.println("Please type the name of the electrical technique: ");
            String tempTechnique = s.next();
            techniques.add(tempTechnique);
        }
        String query = "SELECT technique_name, technique_consumption FROM techniques WHERE technique_name = ?";
        Double totalAmpere = 0.0;
        for (int i = 0; i < number; i++) {
            try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
                ps.setString(1, techniques.get(i));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    double tempPower = rs.getDouble("technique_consumption") / 220;
                    technique_power.add(tempPower);
                    totalAmpere+=tempPower;
                }
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        for (int i = 0; i < number; i++) {
            System.out.println("Technique " + techniques.get(i) + " have power of " + technique_power.get(i) + " amperes!");
        }
        System.out.println("The fuse in your house should support at least " + totalAmpere+ " amperes!");
        System.out.println("\n");
    }
    public void fourthChoice() throws SQLException {
        System.out.println("Thank you for using our Electricity Billing System!");
        database.getConnection().close();
    }
}