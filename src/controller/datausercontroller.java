/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import DAOdatauser.datauserDAO;
import DAOImplement.datauserimplement;
import model.*;
import hotelmanagementsystem.*;
import javax.swing.JOptionPane;


public class datausercontroller {
    private signup frame;
    private datauserimplement impldatauser;
    private List<dataUser> dataUserList;

    public datausercontroller(signup frame) {
        this.frame = frame;
        this.impldatauser = new datauserDAO();
    }
    
    public datausercontroller() {
        this.impldatauser = new datauserDAO();
    }

    public void insert() {
        try {
            String username = frame.getJTxtUsername().getText().trim();
            String email = frame.getJTxtEmail().getText().trim();
            String password = new String(frame.getjPasswordField().getPassword()).trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            dataUser user = new dataUser();
            user.setUsername(username);
            user.setEmail_user(email);
            user.setPassword_user(password);

            
            impldatauser.insert(user);

            
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public dataUser login(String username, String password) {
        return impldatauser.getByUsernameAndPassword(username, password);
    }

}
