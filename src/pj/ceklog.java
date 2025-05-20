/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Bagaskara
 */
public class ceklog {
    public static ResultSet getData(String query){
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            conn=connector.connection();
            st=conn.createStatement();
            rs=st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
}
