/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import DAOdata.datauserDAO;
import DAOImplement.datauserimplement;
import model.*;
import hotelmanagementsystem.*;
import javax.swing.JOptionPane;

public class datausercontroller {

    private signup signupFrame;
    private adminHome adminFrame;
    private userHome userFrame;
    private datauserimplement impldatauser;
    private List<dataUser> dataUserList;

    public datausercontroller(signup frame) {
        this.signupFrame = frame;
        this.impldatauser = new datauserDAO();
    }

    public datausercontroller(login frame) {
        this.impldatauser = new datauserDAO();
    }

    public datausercontroller(adminHome frame) {
        this.adminFrame = frame;
        this.impldatauser = new datauserDAO();
    }
    
    public datausercontroller(userHome frame) {
        this.userFrame = frame;
        this.impldatauser = new datauserDAO();
    }
    
    public datausercontroller() {
        this.impldatauser = new datauserDAO();
    }

    public void signup() {
        if (signupFrame == null) {
            return;
        }

        try {
            String username = signupFrame.getJTxtUsername().getText().trim();
            String email = signupFrame.getJTxtEmail().getText().trim();
            String password = new String(signupFrame.getjPasswordField().getPassword()).trim();

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
            signupFrame.dispose();
            new login().setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public dataUser login(String username, String password) {
        return impldatauser.getByUsernameAndPassword(username, password);
    }

    public void isiTabel() {
        if (adminFrame == null) {
            return;
        }

        dataUserList = impldatauser.getAll();
        modeltabeldatauser tableModel = new modeltabeldatauser(dataUserList);
        adminFrame.getTabelUser().setModel(tableModel);

        adminFrame.getTabelUser().getColumnModel().getColumn(0).setMinWidth(0);
        adminFrame.getTabelUser().getColumnModel().getColumn(0).setMaxWidth(0);
        adminFrame.getTabelUser().getColumnModel().getColumn(0).setWidth(0);
    }

    public void delete() {
        int selectedRow = adminFrame.getTabelUser().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Object idObj = adminFrame.getTabelUser().getValueAt(selectedRow, 0);
        if (idObj == null) {
            JOptionPane.showMessageDialog(null, "ID user tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id_user = Integer.parseInt(idObj.toString());
        impldatauser.delete(id_user);

        isiTabel();
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
    }
    
    public void cariuser(String keyword) {
    try {
            if (keyword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Silakan masukkan kata kunci pencarian.");
            } else {
                List<dataUser> hasil = impldatauser.cari(keyword);
                if (hasil.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Data dengan kata kunci tersebut tidak ditemukan.");
                } else {
                    modeltabeldatauser model = new modeltabeldatauser(hasil);
                    adminFrame.getTabelUser().setModel(model);
                }
            } 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari data: " + ex.getMessage());
        }
    
        adminFrame.getTabelUser().getColumnModel().getColumn(0).setMinWidth(0);
        adminFrame.getTabelUser().getColumnModel().getColumn(0).setMaxWidth(0);
        adminFrame.getTabelUser().getColumnModel().getColumn(0).setWidth(0);
    }

}
