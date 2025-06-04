package DAOdata;

import java.sql.*;
import java.util.*;
import pj.connector;
import model.*;
import DAOImplement.dataroomimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class dataroomDAO implements dataroomimplement {
    
    private final Connection connection;
    
    final String select = "SELECT * FROM room";
    final String add = "INSERT INTO room (room_number, room_type, bed_size, price, status) VALUES (?, ?, ?, ?, 'Not Booked')";
    final String booked = "UPDATE room SET status = 'Booked' WHERE room_number = ?";
    final String getRoom = "SELECT * FROM room WHERE room_type = ? AND bed_size = ? AND status = 'Not Booked'";
    final String delete = "DELETE FROM room where id_room=?";

    public dataroomDAO(){
        connection = connector.connection();
    }

    @Override
    public void add(dataRoom room) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, room.getRoom_number());
            statement.setString(2, room.getRoom_type());
            statement.setString(3, room.getBed_size());
            statement.setInt(4, room.getPrice());
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                room.setId_room(rs.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Data Room Berhasil Ditambahkan.");
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void updateRoomStatus(dataRoom room) {
        try (PreparedStatement statement = connection.prepareStatement(booked)) {
            String roomNum = room.getRoom_number().trim();
            statement.setString(1, roomNum);
            System.out.println("Updating room status for room_number: '" + roomNum + "'");

            int updatedRows = statement.executeUpdate();
            System.out.println("Rows updated: " + updatedRows);
            if (updatedRows == 0) {
                System.out.println("Warning: No room found with room_number: '" + roomNum + "'");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<dataRoom> getAll() {
        List<dataRoom> data = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()) {
                dataRoom room = new dataRoom();
                room.setId_room(rs.getInt("id_room"));
                room.setRoom_number(rs.getString("room_number"));
                room.setRoom_type(rs.getString("room_type"));
                room.setBed_size(rs.getString("bed_size"));
                room.setPrice(rs.getInt("price"));
                room.setStatus(rs.getString("status"));
                data.add(room);
            }
        } catch(SQLException ex) {
            Logger.getLogger(dataroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    @Override
    public void delete(int id_room) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);

            statement.setInt(1, id_room);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public List<dataRoom> getRoombyInput(String type, String bed) {
        List<dataRoom> data = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(getRoom)) {
            statement.setString(1, type);
            statement.setString(2, bed);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dataRoom room = new dataRoom();
                room.setId_room(rs.getInt("id_room"));
                room.setRoom_number(rs.getString("room_number"));
                room.setRoom_type(rs.getString("room_type"));
                room.setBed_size(rs.getString("bed_size"));
                room.setPrice(rs.getInt("price"));
                room.setStatus(rs.getString("status"));
                data.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
}
