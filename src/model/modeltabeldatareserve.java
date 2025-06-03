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
public class modeltabeldatareserve extends AbstractTableModel {

    private List<dataReserve> list;
    
    public modeltabeldatareserve(List<dataReserve> list) {
        this.list = list;
    }
    
    private final String columnNames[] = {
        "ID",
        "Name",
        "Phone Number",
        "Gender",
        "Email",
        "ID Proof",
        "Address",
        "Check-In Date",
        "Room Number",
        "Bed Size",
        "Room Type",
        "Price per Day"
    };
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column) {
            case 0:
                return list.get(row).getId_reserve();
            case 1:
                return list.get(row).getName();
            case 2:
                return list.get(row).getPhone_number();
            case 3:
                return list.get(row).getGender();
            case 4:
                return list.get(row).getEmail();
            case 5:
                return list.get(row).getId_proof();
            case 6:
                return list.get(row).getAddress();
            case 7:
                return list.get(row).getCheckin_date();
            case 8:
                return list.get(row).getRoom_number();
            case 9:
                return list.get(row).getBed_size();
            case 10:
                return list.get(row).getRoom_type();
            case 11:
                return list.get(row).getPrice();
            default:
                return null;
        }
    }
    
}
