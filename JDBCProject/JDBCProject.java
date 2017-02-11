/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcproject;

import java.sql.*;

/**
 *
 * @author Anton
 */
public class JDBCProject {
    
    private static final String JDBC_Connection_Manager_Name =  "com.mysql.jdbc.Driver";
    private static final String Connection_URL = "jdbc:mysql://localhost:3306/employeeschema";
    
    /** NOTE! You should specify the username and password for your own MySql Database
        You should also add the library mysql-connector-java-5.1.13-bin.jar to your project*/
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    
    private static Connection connection;
    
    static {
            try {            
               Class.forName(JDBC_Connection_Manager_Name).newInstance();
               connection =  DriverManager.getConnection(Connection_URL, USER, PASSWORD);
            } 
            catch (InstantiationException ex) {
                System.out.println(ex);
            } 
            catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }  
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    
    public static void selectFromDB(){
        try {
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employer");
            
            while(resultSet.next()){
                System.out.println(resultSet.getString("firstName") + 
                        " " + resultSet.getString("lastName") +
                        " " + resultSet.getString("salary"));
            }
            
        } 
        catch (SQLException ex) {
           System.out.println(ex);
        }
    }
    
    public static void insertIntoDB(String firstName, String lastName, int salary){
        
        try {
            
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Employer (firstName, lastName, salary)" +
                    "VALUES('" + firstName + "', '" + lastName + "', '" + salary + "')");
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void updateDB(String updateParam, String newValue, 
            String whereCondition, String conditionValue){
         try {
             
            Statement statement = connection.createStatement();
            //int rowsCount = statement.executeUpdate("UPDATE Employer SET salary = 700 WHERE " + 
                    //"firstName =  'Emil';");
            int rowsCount = statement.executeUpdate("UPDATE Employer SET " + updateParam + " = '" + newValue + "' WHERE " + 
                    whereCondition + " = '" + conditionValue + "';");
            
            System.out.println(rowsCount + " rows in Employer table updated");

        } 
         catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        
        insertIntoDB("Petar", "Ivanov", 300);
        insertIntoDB("Petar", "Petkov", 450);
        insertIntoDB("Dimitar", "Ivanov", 650);
        insertIntoDB("Dimitar", "Petkov", 700);
        
        selectFromDB();
        updateDB("salary", "550", "lastName", "Petkov");
        selectFromDB();
    }
}
