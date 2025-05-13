package DAO;

import models.Products;
import DAO.DbOperations;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class orderDao {
        public static boolean save(List<Products> products){
            String UNIQUE_ID = "001"; //tempo id
            
            for (Products p : products) {
                System.out.println(p.getName() + ", Qty: " + p.getQuantity() + ", Price: " + p.getPrice()); // printing for debugging, to-remove.
                String query = "INSERT INTO orders (CustomerID, Qty, Products, Price) VALUES('"+UNIQUE_ID+"', '"+p.getQuantity()+"', '"+p.getName()+"', '"+p.getPrice()+"')";
                if(!DbOperations.SetOrDeleteData(query)){
                    return false; // if even one error in the execution of query, it returns false immediately
                }              
            }
           return true; // note: currently using regular statements prone to sql injection. to-do: used prepared statements, tinatamad pa'ko.
    }
        
    public static List<Products> getOrdersByCustomerId(int customerId) {
        List<Products> orderedProducts = new ArrayList<>();
        try {
            Connection con = ConnectionProvider.createConnection();
            String query = "SELECT products, qty, price FROM orders WHERE customerID = ? AND status != 'Paid'";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, customerId);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("products");
                int quantity = rs.getInt("Qty");
                double price = rs.getDouble("Price");

                Products product = new Products(productName, (int) price);
                product.setQuantity(quantity);

                orderedProducts.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderedProducts;
    }
    
    public static boolean updateOrderStatus(int customerId, String status) {
        try {
            Connection con = ConnectionProvider.createConnection();
            String query = "UPDATE orders SET status = ? WHERE customerID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, status);
            pst.setInt(2, customerId);

            int updatedRows = pst.executeUpdate(); // every successful update, increments this var
            return updatedRows > 0;  // return true if rows updated is greater than 0
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // return false if nothing is updated
        }
    }
    
    public static boolean deleteOrderByCustomerId(int customerId) {
        try {
            Connection con = ConnectionProvider.createConnection();
            String query = "DELETE FROM orders WHERE customerID = ? AND status = 'Canceled'";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, customerId);

            int deletedRows = pst.executeUpdate(); // every successful daletion, increments this var
            return deletedRows > 0; // return true if rows deleted is greater than 0
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // return false if nothing is deleted
        }
    }
}
/* to do  list: 
P: Ordered product list needs reset for every successful db insertion
A: make a method for it. still needs further study here.

P: Using temporary "unique" id(001) to identify customers from the db.
A: for every program run make a variable that increments or makes another unique id after each order insertion to the db

Features: 
# login and signup to create unique id for customers and integrate discount == just change the final value of discount.
# for order now(guest) make a unique id that changes for every successful orders which im going to implement first before Login and signup. 

# order confirmation popup before insertion to the database

# cashier view
    -takes orders from customerID
    -confirm payment
        -clears customer pending order from database

# qr code idea
    encrypts customer orders into qr code to be scanned by cashier view 
    
requires:
    - qr code generator
    - qr code scanner that uses client camera
    - prints qr code into a file to client's computer
    - receives printed qr code to confirm order
*/
