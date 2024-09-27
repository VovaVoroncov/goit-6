import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseTest {

    private Database database;
    private Connection mockConnection;

    @BeforeEach
    void setUp() throws SQLException {
        // Mock the connection
        mockConnection = mock(Connection.class);
        // Mock DriverManager to return the mocked connection
        DriverManager.registerDriver(new MockDriver(mockConnection));

        // Initialize Database instance
        database = Database.getInstance();
    }

    @Test
    void testGetInstance() {
        Database anotherInstance = Database.getInstance();
        assertSame(database, anotherInstance, "Database should be a singleton.");
    }

    @Test
    void testGetConnection() throws SQLException {
        // Call getConnection
        Connection connection = database.getConnection();

        // Assertions
        assertNotNull(connection);
        assertSame(mockConnection, connection, "Should return the same mock connection.");

        // Call getConnection again to check the same instance is returned
        Connection anotherConnection = database.getConnection();
        assertSame(mockConnection, anotherConnection, "Should return the same connection if not closed.");
    }

    @Test
    void testGetConnectionWhenClosed() throws SQLException {
        // Simulate that the connection is closed
        when(mockConnection.isClosed()).thenReturn(true);

        // Call getConnection
        Connection connection = database.getConnection();

        // Assertions
        assertNotNull(connection);
        verify(mockConnection, times(1)).isClosed();
        assertSame(mockConnection, connection, "Should create a new connection if the previous one is closed.");
    }

    @Test
    void testLoadPropertiesFail() {
        // Simulate a failure to load properties
        assertThrows(RuntimeException.class, () -> {
            new Database() {
                @Override
                protected void loadProperties() throws IOException {
                    throw new IOException("Failed to load properties");
                }
            };
        });
    }

    private static class MockDriver extends java.sql.Driver {
        private final Connection mockConnection;

        public MockDriver(Connection mockConnection) {
            this.mockConnection = mockConnection;
        }

        @Override
        public Connection connect(String url, java.util.Properties info) throws SQLException {
            return mockConnection;
        }

        @Override
        public boolean acceptsURL(String url) {
            return true;
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, java.util.Properties info) throws SQLException {
            return new DriverPropertyInfo[0];
        }

        @Override
        public int getMajorVersion() {
            return 0;
        }

        @Override
        public int getMinorVersion() {
            return 0;
        }

        @Override
        public boolean jdbcCompliant() {
            return false;
        }
    }
}
