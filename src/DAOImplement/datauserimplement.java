/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplement;
import java.util.List;
import model.dataUser;
/**
 *
 * @author Bagaskara
 */
public interface datauserimplement {
    public void insert(dataUser u);
    public void delete(int id_user);
    public List<dataUser> getAll();
    public dataUser getByUsernameAndPassword(String username, String password);
    public List<dataUser> cari(String keyword);
}
