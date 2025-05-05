package DAO;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
        
public class ConnectionProvider {
       private static final String USER = "root";
       private static final String PASSWORD = "101609";
       
         public static Connection createConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Loads the MySQL JDBC (Java Database Connectivity) driver class into memory.
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_fastfood", USER, PASSWORD); //  This method establishes the connection to the database.
                return con;
            }
            catch(ClassNotFoundException ex) { // If the JDBC driver class is missing.  
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(SQLException ex){ //  If there is an issue with the connection itself
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null , ex);
            }
            return null;
         }  
}
