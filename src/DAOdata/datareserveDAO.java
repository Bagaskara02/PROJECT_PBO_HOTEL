/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdata;

import java.sql.*;
import java.util.*;
import pj.connector;
import model.*;
import DAOImplement.datareserveimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class datareserveDAO implements datareserveimplement {
    
    private final Connection connection;
    
    final String select = "SELECT * FROM reserve";
    final String reserve = "INSERT INTO reserve (name, phone_number, gender, email, id_proof, address, checkin_date, room_type, bed_size, room_number, price, total_day, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public datareserveDAO() {
        connection = connector.connection();
    }
    
    @Override
    public void reserve(dataReserve r) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(reserve, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, r.getName());
            statement.setString(2, r.getPhone_number());
            statement.setString(3, r.getGender());
            statement.setString(4, r.getEmail());
            statement.setString(5, r.getId_proof());
            statement.setString(6, r.getAddress());
            statement.setString(7, r.getCheckin_date());
            statement.setString(8, r.getRoom_type());
            statement.setString(9, r.getBed_size());
            statement.setString(10, r.getRoom_number());
            statement.setInt(11, r.getPrice());
            statement.setInt(12, r.getTotal_day());
            statement.setInt(13, r.getTotal_amount());
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()) {
                r.setId_reserve(rs.getInt(1));
            }
        } catch(SQLException ex) {
           ex.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<dataReserve> getAll() {
        List<dataReserve> data = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()) {
                dataReserve reserve = new dataReserve();
                reserve.setId_reserve(rs.getInt("id_reserve"));
                reserve.setName(rs.getString("name"));
                reserve.setPhone_number(rs.getString("phone_number"));
                reserve.setGender(rs.getString("gender"));
                reserve.setEmail(rs.getString("email"));
                reserve.setId_proof(rs.getString("id_proof"));
                reserve.setAddress(rs.getString("address"));
                reserve.setCheckin_date(rs.getString("checkin_date"));
                reserve.setRoom_type(rs.getString("room_type"));
                reserve.setBed_size(rs.getString("bed_size"));
                reserve.setRoom_number(rs.getString("room_number"));
                reserve.setPrice(rs.getInt("price"));
                reserve.setTotal_day(rs.getInt("total_day"));
                reserve.setTotal_amount(rs.getInt("total_amount"));
                
                data.add(reserve);
            }
        } catch(SQLException ex) {
            Logger.getLogger(datareserveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
}
