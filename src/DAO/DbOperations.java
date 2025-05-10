package DAO;

import javax.swing.JOptionPane;
import java.sql.*;

public class DbOperations {
    public static boolean SetOrDeleteData(String query){
        try {
            Connection con = ConnectionProvider.createConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);       
            return true; // for succesfull execution of query
        }
        catch(Exception e){
            return false;
        }

    }
}
