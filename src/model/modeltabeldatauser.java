/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bagaskara
 */
public class modeltabeldatauser extends AbstractTableModel {

    private List<dataUser> list;

    public modeltabeldatauser(List<dataUser> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3; // ID, Username, Email
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getId_user();
            case 1:
                return list.get(rowIndex).getUsername();
            case 2:
                return list.get(rowIndex).getEmail_user();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Username";
            case 2:
                return "Email";
            default:
                return null;
        }
    }
}
