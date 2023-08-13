package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins (first_name, last_name, email, password, superadmin, enable) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";

    public void create(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(CREATE_ADMIN_QUERY);
            preStmt.setString(1, admin.getFirstName());
            preStmt.setString(2, admin.getLastName());
            preStmt.setString(3, admin.getEmail());
            preStmt.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            preStmt.setBoolean(5, admin.isSuperadmin());
            preStmt.setBoolean(6, admin.isEnable());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin read(int adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getBoolean("superadmin"));
                    admin.setEnable(resultSet.getBoolean("enable"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public List<Admin> findAll() {
        List<Admin> listOfAllAdmins = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setFirstName(rs.getString("first_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setSuperadmin(rs.getBoolean("superadmin"));
                admin.setEnable(rs.getBoolean("enable"));
                listOfAllAdmins.add(admin);
            }
            return listOfAllAdmins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preStmt.setString(1, admin.getFirstName());
            preStmt.setString(2, admin.getLastName());
            preStmt.setString(3, admin.getEmail());
            preStmt.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            preStmt.setBoolean(5, admin.isSuperadmin());
            preStmt.setBoolean(6, admin.isEnable());
            preStmt.setInt(7, admin.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int adminId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(DELETE_ADMIN_QUERY);
            preStmt.setInt(1, adminId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createNewAdmin(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, admin.getFirstName());
            preStmt.setString(2, admin.getLastName());
            preStmt.setString(3, admin.getEmail());
            preStmt.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            preStmt.setBoolean(5, admin.isSuperadmin());
            preStmt.setBoolean(6, admin.isEnable());
            preStmt.executeUpdate();

            try (ResultSet generatedKeys = preStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin authenticate(String email, String password) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM admins WHERE email = ?")) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        Admin admin = new Admin();
                        admin.setId(resultSet.getInt("id"));
                        admin.setFirstName(resultSet.getString("first_name"));
                        admin.setLastName(resultSet.getString("last_name"));
                        admin.setEmail(resultSet.getString("email"));
                        admin.setSuperadmin(resultSet.getBoolean("superadmin"));
                        admin.setEnable(resultSet.getBoolean("enable"));
                        return admin;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
