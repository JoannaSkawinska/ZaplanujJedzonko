package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String CREATE_DAY_NAME_QUERY = "INSERT INTO day_name(name, display_order) VALUES (?, ?);";
    private static final String DELETE_DAY_NAME_QUERY = "DELETE FROM day_name WHERE id = ?;";
    private static final String FIND_ALL_DAY_NAMES_QUERY = "SELECT * FROM day_name;";
    private static final String READ_DAY_NAME_QUERY = "SELECT * FROM day_name WHERE id = ?;";
    private static final String UPDATE_DAY_NAME_QUERY = "UPDATE day_name SET name = ?, display_order = ? WHERE id = ?;";

    public void create(DayName dayName) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(CREATE_DAY_NAME_QUERY);
            preStmt.setString(1, dayName.getName());
            preStmt.setInt(2, dayName.getDisplayOrder());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DayName read(int dayNameId) {
        DayName dayName = new DayName();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_DAY_NAME_QUERY)
        ) {
            statement.setInt(1, dayNameId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    dayName.setId(resultSet.getInt("id"));
                    dayName.setName(resultSet.getString("name"));
                    dayName.setDisplayOrder(resultSet.getInt("display_order"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayName;
    }

    public List<DayName> findAll() {
        List<DayName> listOfAllDayNames = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL_DAY_NAMES_QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DayName dayName = new DayName();
                dayName.setId(rs.getInt("id"));
                dayName.setName(rs.getString("name"));
                dayName.setDisplayOrder(rs.getInt("display_order"));
                listOfAllDayNames.add(dayName);
            }
            return listOfAllDayNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(DayName dayName) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(UPDATE_DAY_NAME_QUERY);
            preStmt.setString(1, dayName.getName());
            preStmt.setInt(2, dayName.getDisplayOrder());
            preStmt.setInt(3, dayName.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int dayNameId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(DELETE_DAY_NAME_QUERY);
            preStmt.setInt(1, dayNameId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
