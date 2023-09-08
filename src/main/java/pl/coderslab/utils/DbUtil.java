/*package pl.coderslab.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_PARAMS = "?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    //jdbc:mysql://localhost:3306/scrumlab?allowPublicKeyRetrieval=true&seSSL=false&characterEncoding=utf8&serverTimezone=UTC
    private static final String DEFAULT_DB_NAME = "scrumlab";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "coderslab";

    public static Connection getConnection() throws SQLException {
        return getConnection(DEFAULT_DB_NAME);

    }

    public static Connection getConnection (String dbName) throws SQLException {
        String fullUrl = DB_URL + "/" + dbName + DB_PARAMS;

        return DriverManager.getConnection(
                fullUrl,
                DB_USER,
                DB_PASSWORD
        );
    }
    public static void insert (Connection conn, String query, String...params){
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }





}*/


package pl.coderslab.utils;

        import javax.naming.Context;
        import javax.naming.InitialContext;
        import javax.naming.NamingException;
        import javax.sql.DataSource;
        import java.sql.Connection;
        import java.sql.SQLException;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;

public class DbUtil {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    private static DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/scrumlab");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
    public static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
