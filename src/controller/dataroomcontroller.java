/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import DAOdata.dataroomDAO;
import DAOImplement.dataroomimplement;
import model.*;
import hotelmanagementsystem.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class dataroomcontroller {

    private manageRoom roomFrame;
    private dataroomimplement impldataroom;
    private List<dataRoom> dataRoomList;

    public dataroomcontroller(manageRoom frame) {
        this.roomFrame = frame;
        this.impldataroom = new dataroomDAO();
    }

    public void isiTabel() {
        if (roomFrame == null) {
            return;
        }

        dataRoomList = impldataroom.getAll();
        modeltabeldataroom tableModel = new modeltabeldataroom(dataRoomList);
        roomFrame.getTabelRoom().setModel(tableModel);

        roomFrame.getTabelRoom().getColumnModel().getColumn(0).setMinWidth(0);
        roomFrame.getTabelRoom().getColumnModel().getColumn(0).setMaxWidth(0);
        roomFrame.getTabelRoom().getColumnModel().getColumn(0).setWidth(0);
    }

    public void add() {
        dataRoom room = new dataRoom();

        JTextField[] roomFields = {
            roomFrame.getjTextRoomNumber(),
            roomFrame.getjTextPrice()
        };

        for (JTextField field : roomFields) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(roomFrame, "Tolong isi semua data!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // ComboBox tetap dicek juga kalau perlu
        if (roomFrame.getjComboBoxRoomType().getSelectedIndex() == -1 || roomFrame.getjComboBoxBedSize().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(roomFrame, "Pilih semua opsi ComboBox!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        room.setRoom_number(roomFrame.getjTextRoomNumber().getText());
        room.setRoom_type(roomFrame.getjComboBoxRoomType().getSelectedItem().toString());
        room.setBed_size(roomFrame.getjComboBoxBedSize().getSelectedItem().toString());

        try {
            int price = Integer.parseInt(roomFrame.getjTextPrice().getText());
            room.setPrice(price);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(roomFrame, "Harga harus berupa angka!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Simpan ke database
        impldataroom.add(room);
    }
}
