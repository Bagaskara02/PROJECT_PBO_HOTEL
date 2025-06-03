/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class modeltabeldataroom extends AbstractTableModel {

    private List<dataRoom> list;
    
    public modeltabeldataroom(List<dataRoom> list){
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6; // roomNumber, roomType, bedSize, price, status
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column) {
            case 0:
                return list.get(row).getId_room();
            case 1:
                return list.get(row).getRoom_number();
            case 2:
                return list.get(row).getRoom_type();
            case 3:
                return list.get(row).getBed_size();
            case 4:
                return list.get(row).getPrice();
            case 5:
                return list.get(row).getStatus();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Id Room";
            case 1:
                return "Room Number";
            case 2:
                return "Room Type";
            case 3:
                return "Bed Size";
            case 4:
                return "Price";
            case 5:
                return "Status";
            default:
                return null;
        }
    }
}
