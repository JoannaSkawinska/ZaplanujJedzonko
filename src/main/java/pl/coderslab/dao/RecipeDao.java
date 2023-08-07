package pl.coderslab.dao;

import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE	recipe SET name = ?, ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, preparation = ? WHERE	id = ?;";

    public static void createNewRecipe (Recipe recipe) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(CREATE_RECIPE_QUERY);
            preStmt.setString(1, recipe.getName());
            preStmt.setString(2, recipe.getIngredients());
            preStmt.setString(3, recipe.getDescription());
            preStmt.setString(4, recipe.getCreated());
            preStmt.setString(5, recipe.getUpdated());
            preStmt.setInt(6, recipe.getPreparationTime());
            preStmt.setString(7, recipe.getPreparation());
            preStmt.setInt(8, recipe.getAdminId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }
    public static List<Recipe> findAllRecipes () {
        List<Recipe> listOfAllRecipes = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(rs.getInt("id"));
                recipe.setName(rs.getString("name"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setDescription(rs.getString("description"));
                recipe.setCreated(rs.getString("created"));
                recipe.setUpdated(rs.getString("updated"));
                recipe.setPreparationTime(rs.getInt("preparation_time"));
                recipe.setPreparation(rs.getString("preparation"));
                recipe.setAdminId(rs.getInt("admin_id"));
                listOfAllRecipes.add(recipe);
            }
            return listOfAllRecipes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateRecipe (Recipe recipe) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(UPDATE_RECIPE_QUERY);
            preStmt.setString(1, recipe.getName());
            preStmt.setString(2, recipe.getIngredients());
            preStmt.setString(3, recipe.getDescription());
            preStmt.setString(4, recipe.getCreated());
            preStmt.setString(5, recipe.getUpdated());
            preStmt.setInt(6, recipe.getPreparationTime());
            preStmt.setString(7, recipe.getPreparation());
            preStmt.setInt(8, recipe.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecipe (int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preStmt = connection.prepareStatement(DELETE_RECIPE_QUERY);
            preStmt.setInt(1, recipeId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}