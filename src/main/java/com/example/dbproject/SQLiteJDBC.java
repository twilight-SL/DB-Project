package com.example.dbproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteJDBC {
    /* SQL INSERT Operation */
    public static void insertion(String inputCommand)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hgb.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate(inputCommand);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Inserted successfully");
    }

    /* SQL SELECT Operation */
    public static void selection(String tableName, String inputCommand)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hgb.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(inputCommand);

            switch(tableName){
                case "Hotel Group Brand":
                    while ( rs.next() ) {
                        String name = rs.getString("Name");
                        String country = rs.getString("Country");
                        int businessNo = rs.getInt("Business No");
                        String address = rs.getString("Address");
                        String phone = rs.getString("Phone");
                        int rating = rs.getInt("Rating");
                        String ManagerID = rs.getString("Manager Id");

                        System.out.print( "Name: " + name + "/ ");
                        System.out.print( "Country: " + country + "/ ");
                        System.out.print( "Business No: " + businessNo + "/ ");
                        System.out.print( "Address: " + address + "/ ");
                        System.out.print( "Phone: " + phone + "/ ");
                        System.out.print( "Rating: " + rating + "/ ");
                        System.out.print( "Manager Id: " + ManagerID);
                        System.out.println();
                    }
                    break;
                case "Chain Hotel":
                    while ( rs.next() ) {
                        String name = rs.getString("Name");
                        String branchNo = rs.getString("Branch No");
                        String country = rs.getString("Country");
                        String address = rs.getString("Address");
                        int HGB_businessNo = rs.getInt("HGB_Business No");
                        String phone = rs.getString("Phone");
                        int star = rs.getInt("Star");
                        String ManagerID = rs.getString("Manager Id");

                        System.out.print( "Name: " + name + "/ ");
                        System.out.print( "Branch No: " + branchNo + "/ ");
                        System.out.print( "Country: " + country + "/ ");
                        System.out.print( "Address: " + address + "/ ");
                        System.out.print( "HGB_Business No: " + HGB_businessNo + "/ ");
                        System.out.print( "Phone: " + phone + "/ ");
                        System.out.print( "Star: " + star + "/ ");
                        System.out.print( "Manager Id: " + ManagerID);
                        System.out.println();
                    }
                    break;
                case "Sub-Brand":
                    while ( rs.next() ) {
                        String name = rs.getString("Name");
                        int HGB_businessNo = rs.getInt("HGB_Business No");
                        int businessNo = rs.getInt("Business No");
                        String country = rs.getString("Country");
                        String address = rs.getString("Address");
                        String category = rs.getString("c");
                        int rating = rs.getInt("Rating");
                        String ManagerID = rs.getString("Manager Id");

                        System.out.print( "Name: " + name + "/ ");
                        System.out.print( "HGB_Business No: " + HGB_businessNo + "/ ");
                        System.out.print( "Business No: " + businessNo + "/ ");
                        System.out.print( "Country: " + country + "/ ");
                        System.out.print( "Address: " + address + "/ ");
                        System.out.print( "Category: " + category + "/ ");
                        System.out.print( "Rating: " + rating + "/ ");
                        System.out.print( "Manager Id: " + ManagerID);
                        System.out.println();
                    }
                    break;
                case "Employee":
                    while ( rs.next() ) {
                        String id = rs.getString("ID");
                        String fName = rs.getString("FName");
                        String lName = rs.getString("LName");
                        String gender = rs.getString("Gender");
                        String bDate = rs.getString("BDate");
                        String supervisorID = rs.getString("Supervisor Id");
                        int HGB_businessNo = rs.getInt("HGB_Business No");

                        System.out.print( "ID: " + id + "/ ");
                        System.out.print( "FName: " + fName + "/ ");
                        System.out.print( "LName: " + lName + "/ ");
                        System.out.print( "Gender: " + gender + "/ ");
                        System.out.print( "BDate: " + bDate + "/ ");
                        System.out.print( "Supervisor Id: " + supervisorID + "/ ");
                        System.out.print( "HGB_Business No: " + HGB_businessNo);
                        System.out.println();
                    }
                    break;
                case "Customer":
                    while ( rs.next() ) {
                        String id = rs.getString("ID");
                        String fName = rs.getString("FName");
                        String lName = rs.getString("LName");
                        String phone = rs.getString("phone");
                        String roomNo = rs.getString("Room No");
                        String Date = rs.getString("Date");
                        int day = rs.getInt("Day");

                        System.out.print( "ID: " + id + "/ ");
                        System.out.print( "FName: " + fName + "/ ");
                        System.out.print( "LName: " + lName + "/ ");
                        System.out.print( "phone: " + phone + "/ ");
                        System.out.print( "Room No: " + roomNo + "/ ");
                        System.out.print( "Date: " + Date + "/ ");
                        System.out.print( "Day: " + day);
                        System.out.println();
                    }
                    break;
                case "Room":
                    while ( rs.next() ) {
                        String hgb_b_roomNo = rs.getString("HGB_B_Room No");
                        String type = rs.getString("Type");
                        String date = rs.getString("Date");
                        int day = rs.getInt("Day");
                        String status = rs.getString("Status");
                        int HGB_businessNo = rs.getInt("HGB_Business No");
                        int HGB_branchNo = rs.getInt("HGB_Branch No");

                        System.out.print( "HGB_B_Room No: " + hgb_b_roomNo + "/ ");
                        System.out.print( "Type: " + type + "/ ");
                        System.out.print( "Date: " + date + "/ ");
                        System.out.print( "Day: " + day + "/ ");
                        System.out.print( "Status: " + status + "/ ");
                        System.out.print( "HGB_Business No: " + HGB_businessNo + "/ ");
                        System.out.print( "HGB_Branch No: " + HGB_branchNo + "/ ");
                        System.out.println();
                    }
                    break;
                case "Payment":
                    while ( rs.next() ) {
                        String transactionId = rs.getString("Transaction Id");
                        String customerId = rs.getString("Customer Id");
                        String date = rs.getString("Date");
                        String method = rs.getString("Method");
                        int amount = rs.getInt("Amount");
                        String description = rs.getString("Description");

                        System.out.print( "Transaction Id: " + transactionId + "/ ");
                        System.out.print( "Customer Id: " + customerId + "/ ");
                        System.out.print( "Date: " + date + "/ ");
                        System.out.print( "Method: " + method + "/ ");
                        System.out.print( "Amount: " + amount + "/ ");
                        System.out.print( "Description: " + description);
                        System.out.println();
                    }
                    break;
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Select done successfully");
    }

    /* SQL UPDATE Operation */
    public static void update(String inputCommand)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hgb.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate(inputCommand);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Update successfully");
    }

    /* SQL DELETE Operation */
    public static void deletion(String inputCommand)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hgb.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate(inputCommand);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Deletion successfully");
    }

}
