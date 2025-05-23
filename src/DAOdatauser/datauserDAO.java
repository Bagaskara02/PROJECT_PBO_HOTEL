/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatauser;

import java.sql.*;
import java.util.*;
import pj.connector;
import model.*;
import DAOImplement.datauserimplement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bagaskara
 */
public class datauserDAO implements datauserimplement {

    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(datauserDAO.class.getName());

    final String select = "SELECT * FROM user";
    final String insert = "INSERT INTO user (username, email_user, password_user) VALUES (?, ?, ?);";
    final String delete = "DELETE FROM user WHERE id_user = ?";

    public datauserDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(dataUser u) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getEmail_user());
            statement.setString(3, u.getPassword_user());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                u.setId_user(rs.getInt(1));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public dataUser getByUsernameAndPassword(String username, String password) {
        dataUser user = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM user WHERE username = ? AND password_user = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();

            if (rs.next()) {
                user = new dataUser();
                user.setId_user(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setEmail_user(rs.getString("email_user"));
                user.setPassword_user(rs.getString("password_user"));
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public List<dataUser> cari(String keyword) {
        List<dataUser> list = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE username LIKE ? OR email_user LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dataUser user = new dataUser();
                    user.setId_user(rs.getInt("id_user"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail_user(rs.getString("email_user"));
                    list.add(user);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Search failed", ex);
        }

        return list;
    }

    @Override
    public void delete(int id_buku) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id_buku);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public List<dataUser> getAll() {
        List<dataUser> du = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                dataUser bu = new dataUser();
                bu.setId_user(rs.getInt("id_user"));
                bu.setUsername(rs.getString("username"));
                bu.setEmail_user(rs.getString("email_user"));
                du.add(bu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(datauserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return du;
    }
}
