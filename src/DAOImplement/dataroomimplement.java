/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplement;

import java.util.List;
import model.dataRoom;

/**
 *
 * @author User
 */
public interface dataroomimplement {

    public void add(dataRoom room);

    public List<dataRoom> getAll();

    public void updateRoomStatus(dataRoom room);

    public List<dataRoom> getRoombyInput(String type, String bed);
}
