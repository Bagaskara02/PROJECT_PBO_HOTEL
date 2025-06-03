package controller;

import java.util.List;
import DAOdata.datareserveDAO;
import DAOdata.dataroomDAO;
import DAOImplement.datareserveimplement;
import DAOImplement.dataroomimplement;
import model.*;
import hotelmanagementsystem.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class datareservecontroller {
    
    private userCheckIn checkInFrame;
    private datareserveimplement impldatareserve;
    private dataroomimplement impldataroom;
    private List<dataReserve> dataReserveList;
    
    public datareservecontroller(userCheckIn frame) {
        this.checkInFrame = frame;
        this.impldatareserve = new datareserveDAO();
        this.impldataroom = new dataroomDAO();
        
        String checkin_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        checkInFrame.setjTextCheckinDate(checkin_date);
        
        checkInFrame.getjComboRoomType().addActionListener(e -> loadAvailableRooms());
        checkInFrame.getjComboBedSize().addActionListener(e -> loadAvailableRooms());
        
        loadAvailableRooms();
    }
    
    public void isiTabel() { // For Check-Out
        if (checkInFrame == null) {
            return;
        }
        
        dataReserveList = impldatareserve.getAll();
        modeltabeldatareserve tableModel = new modeltabeldatareserve(dataReserveList);
        // checkOutFrame.getTableReserve().setModel(tableModel); // For Check-Out
    }
    
    public void loadAvailableRooms() {
        String selectedType = checkInFrame.getjComboRoomType().getSelectedItem().toString();
        String selectedBed = checkInFrame.getjComboBedSize().getSelectedItem().toString();
        
        List<dataRoom> rooms = impldataroom.getRoombyInput(selectedType, selectedBed);
        
        checkInFrame.getjComboRoomNumber().removeAllItems();
        for(dataRoom room : rooms) {
            checkInFrame.getjComboRoomNumber().addItem(room.getRoom_number());
        }
        
        if (!rooms.isEmpty()) {
            checkInFrame.getjTextPrice().setText(String.valueOf(rooms.get(0).getPrice()));
        } else {
            checkInFrame.getjTextPrice().setText("");
        }
    }
    
    public void reserve() {
        try {
            String name = checkInFrame.getjTextName().getText();
            String phone_number = checkInFrame.getjTextPhoneNumber().getText();
            String gender = checkInFrame.getjComboGender().getSelectedItem().toString();
            String email = checkInFrame.getjTextEmail().getText();
            String id_proof = checkInFrame.getjTextIDProof().getText();
            String address = checkInFrame.getjTextAddress().getText();
            String checkin_date = checkInFrame.getjTextCheckinDate().getText();
            String room_type = checkInFrame.getjComboRoomType().getSelectedItem().toString();
            String bed_size = checkInFrame.getjComboBedSize().getSelectedItem().toString();
            String room_number = checkInFrame.getjComboRoomNumber().getSelectedItem().toString();
            int price = Integer.parseInt(checkInFrame.getjTextPrice().getText());
            
            dataReserve r = new dataReserve();
            r.setName(name);
            r.setPhone_number(phone_number);
            r.setGender(gender);
            r.setEmail(email);
            r.setId_proof(id_proof);
            r.setAddress(address);
            r.setCheckin_date(checkin_date);
            r.setRoom_type(room_type);
            r.setBed_size(bed_size);
            r.setRoom_number(room_number);
            r.setPrice(price);
            
            r.setTotal_day(1);
            r.setTotal_amount(0);
            
            impldatareserve.reserve(r);
            
            dataRoom room = new dataRoom();
            room.setRoom_number(room_number);
            impldataroom.updateRoomStatus(room);
            
            JOptionPane.showMessageDialog(checkInFrame, "Kamar berhasil dipesan.");
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(checkInFrame, "Gagal memesan kamar. " + ex.getMessage());
        }
    }
}
