package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanString;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created, admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String FIND_ALL_PLANS_OF_ADMIN_BY_ID_QUERY = "SELECT * FROM plan where admin_id = ?;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ? WHERE	id = ?;";
    private static final String LAST_PLAN_OF_ADMIN = "SELECT plan.name ,day_name.name as day_name, meal_name, recipe.id, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "            FROM `recipe_plan`\n" +
            "            JOIN day_name on day_name.id=day_name_id\n" +
            "            JOIN recipe on recipe.id=recipe_id\n" +
            "            JOIN plan on plan.id = plan_id WHERE\n" +
            "            recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "            ORDER by day_name.display_order, recipe_plan.display_order;";

    private static final String PLAN_DETAILS_OF_ADMIN = "SELECT plan.name, plan.description, day_name.name as day_name, meal_name, recipe.id, recipe.name as recipe_name, recipe.description as recipe_description, recipe_plan.id\n" +
            "            FROM `recipe_plan`\n" +
            "            JOIN day_name on day_name.id=day_name_id\n" +
            "            JOIN recipe on recipe.id=recipe_id\n" +
            "            JOIN plan on plan.id = plan_id WHERE\n" +
            "            plan.admin_id = ? and plan.id = ?\n" +
            "            ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String RECIPE_ID_FROM_NAME = "SELECT id FROM recipe WHERE name = ?;";
    private static final String NUMBER_OF_PLANS_PER_ADMIN = "SELECT COUNT(plan.id) AS count FROM plan JOIN admins on plan.admin_id = admin_id WHERE admin_id = ?;";

    private static final String DAY_ID_FROM_NAME = "SELECT id FROM day_name WHERE name = ?;";
    private static final String INSERT_RECIPE_TO_PLAN = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_RECIPE_FROM_PLAN = "DELETE FROM recipe_plan WHERE id = ?;";


    public static void createNewPlan (Plan plan) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(CREATE_PLAN_QUERY);
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setString(3, plan.getCreated());
            preStmt.setInt(4, plan.getAdminId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plan;
    }
    public static List<Plan> findAllPlans () {
        List<Plan> listOfAllPlans = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("id"));
                plan.setName(rs.getString("name"));
                plan.setDescription(rs.getString("description"));
                plan.setCreated(rs.getString("created"));
                plan.setAdminId(rs.getInt("admin_id"));
                listOfAllPlans.add(plan);
            }
            return listOfAllPlans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Plan> findAllPlansOfAdmin (int adminId) {
        List<Plan> listOfAllPlansOfAdmin = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL_PLANS_OF_ADMIN_BY_ID_QUERY);
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("id"));
                plan.setName(rs.getString("name"));
                plan.setDescription(rs.getString("description"));
                plan.setCreated(rs.getString("created"));
                plan.setAdminId(rs.getInt("admin_id"));
                listOfAllPlansOfAdmin.add(plan);
            }
            return listOfAllPlansOfAdmin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updatePlan (Plan plan) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(UPDATE_PLAN_QUERY);
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setString(3, plan.getCreated());
            preStmt.setInt(4, plan.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlan (int planId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(DELETE_PLAN_QUERY);
            preStmt.setInt(1, planId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<PlanString> getLastPlanOfAdmin(Admin admin) {
        List<PlanString> planString = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(LAST_PLAN_OF_ADMIN);
            preStmt.setInt(1, admin.getId());
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                PlanString ps = new PlanString();
                ps.setPlanName(rs.getString(1));
                ps.setDayName(rs.getString(2));
                ps.setMealName(rs.getString(3));
                ps.setRecipeId(rs.getString(4));
                ps.setRecipeName(rs.getString(5));
                ps.setRecipeDescription(rs.getString(6));
                planString.add(ps);
            }
            return planString;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PlanString> getListOfPlansOfAdmin (Admin admin, int planId) {
        List<PlanString> listOfPlansOfAdmin = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(PLAN_DETAILS_OF_ADMIN);
            preStmt.setInt(1, admin.getId());
            preStmt.setInt(2, planId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                PlanString ps = new PlanString();
                ps.setPlanName(rs.getString(1));
                ps.setPlanDescription(rs.getString(2));
                ps.setDayName(rs.getString(3));
                ps.setMealName(rs.getString(4));
                ps.setRecipeId(rs.getString(5));
                ps.setRecipeName(rs.getString(6));
                ps.setRecipeDescription(rs.getString(7));
                ps.setRecipePlanId(rs.getString(8));
                listOfPlansOfAdmin.add(ps);
            }
            return listOfPlansOfAdmin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  
    public static int numberOfPlansOfAdmin(Admin admin) {
        int numberOfRecipes = -1;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(NUMBER_OF_PLANS_PER_ADMIN);
            preStmt.setInt(1, admin.getId());
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                numberOfRecipes = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfRecipes;
    }
    public static int getRecipeIdByName(Connection connection, String recipeName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(RECIPE_ID_FROM_NAME)) {
            preparedStatement.setString(1, recipeName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getDayIdByName(Connection connection, String dayName) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(DAY_ID_FROM_NAME)) {
            preparedStatement.setString(1, dayName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void addRecipeToPlan(int planId, String mealName, String recipeName, int dayId, int displayOrder) {
        try (Connection connection = DbUtil.getConnection()) {
            int recipeId = getRecipeIdByName(connection, recipeName);
/*
            int dayId = getDayIdByName(connection, dayName);
*/

            if (recipeId != -1 && dayId != -1) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECIPE_TO_PLAN)) {
                    preparedStatement.setInt(1, recipeId);
                    preparedStatement.setString(2, mealName);
                    preparedStatement.setInt(3, displayOrder);
                    preparedStatement.setInt(4, dayId);
                    preparedStatement.setInt(5, planId);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteRecipeFromPlan(int recipePlanId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(DELETE_RECIPE_FROM_PLAN);
            preStmt.setInt(1, recipePlanId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

