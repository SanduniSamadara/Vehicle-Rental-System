package lk.ijse.FinalProject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection= null;
    private Connection connection;

    //2nd Rulz
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/Troumland_Tours", "root", "1234");
    }
    //3Rd Rulz
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    public Connection getConnection() {
        return connection; // DBConnection.getInstance().getConnection(); Connection eka establish vei

    }
}
