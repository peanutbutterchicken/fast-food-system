package DAO;

import javax.swing.JOptionPane;
import java.sql.*;

public class DbOperations {
    public static void SetOrDeleteData(String query, String message){
        try {
            Connection con = ConnectionProvider.createConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);
            
            if(!message.equals("")){
                JOptionPane.showMessageDialog(null, message);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "fuckkkk", "title", JOptionPane.ERROR_MESSAGE);
        }
    }
}
