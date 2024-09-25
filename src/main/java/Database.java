import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Database INSTANCE = null;
    private static String url = null;
    private static Connection connection;

    private Database() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("settings.properties"));
            url = properties.getProperty("database_url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database.", e);
        }
    }

    public static synchronized Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed.", e);
        }
        return connection;
    }

    public String getUrl() {
        return url;
    }
}
