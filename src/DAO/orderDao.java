package DAO;

import models.Products;
import DAO.DbOperations;
import java.util.List;

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
