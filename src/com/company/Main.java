package com.company;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        Driver driver = new Driver();
        try {
            driver.start();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}