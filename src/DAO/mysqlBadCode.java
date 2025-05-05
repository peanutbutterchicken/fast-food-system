//package DAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.awt.*;
//
//
//import models.Products;
//import controller.OrderController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class mysqlBadCode {
//       private final String USER = "root";
//       private final String PASSWORD = "101609";
//       
//       Connection con=null;
//       PreparedStatement pst = null;
//       
//       String UNIQUE_ID = "001"; // planning to expland this by giving UID's each order instance. For now all orders goes and comes from UID:001.
//                                               // reason: UID to have multiple ordered products in one column.
//       
//        OrderController controller = new OrderController();
//            
//        public void createConnection() { // work-left off: mind is pretty bum or tired can think properly. this code is ai generated, tweaked a little for this app. not working and no errors for some reason.
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver"); // Loads the MySQL JDBC (Java Database Connectivity) driver class into memory.
//                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_fastfood", USER, PASSWORD); //  This method establishes the connection to the database.
//                
//                List<Products> orderedProducts = controller.getOrderedProducts();
//                String query = "INSERT INTO orders (CustomerID, Qty, Products, Price) VALUES(?, ?, ?, ?)";
//
//                pst = con.prepareStatement(query);
//                
//                for (Products p : orderedProducts) {
//                    System.out.println(p.getName() + ", Qty: " + p.getQuantity() + ", Price: " + p.getPrice());
//                    pst.setString(1, "001");        // or whatever type ID is         
//                    pst.setInt(2, p.getQuantity());
//                    pst.setString(3, p.getName());
//                    pst.setInt(4, p.getPrice());
//                    pst.executeUpdate();
//                }
//                System.out.println("Ordered products: " + orderedProducts.size());
//                System.out.println("Database connected!");
//                
//            }
//            catch(ClassNotFoundException ex) { // If the JDBC driver class is missing.  
//                Logger.getLogger(mysqlBadCode.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            catch(SQLException ex){ //  If there is an issue with the connection itself
//                Logger.getLogger(mysqlBadCode.class.getName()).log(Level.SEVERE, null , ex);
//            }
//         }
//
//}

