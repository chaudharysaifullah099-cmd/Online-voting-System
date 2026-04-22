package onlineVotingSystem.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/Online Voting System";
    private static final String USER = "postgres";      // your PostgreSQL username
    private static final String PASSWORD = "1234";     // your PostgreSQL password

    private static Connection conn;

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }
}

