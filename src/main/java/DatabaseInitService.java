import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

public class DatabaseInitService {
    public void initDB() {
        Database database = Database.getInstance();

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(database.getUrl());

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .load();

        flyway.migrate();
    }
}





