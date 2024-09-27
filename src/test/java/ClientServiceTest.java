import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.sql.*;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private Connection connection;
    private ClientService clientService;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class), anyInt())).thenReturn(preparedStatement);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        clientService = new ClientService() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };
    }

    @Test
    void testCreate() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong(1)).thenReturn(1L);

        Long generatedId = clientService.create("John Doe");

        assertEquals(1L, generatedId);
        verify(preparedStatement).setString(1, "John Doe");
    }

    @Test
    void testGetById() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("name")).thenReturn("John Doe");

        String name = clientService.getById(1L);

        assertEquals("John Doe", name);
        verify(preparedStatement).setLong(1, 1L);
    }

    @Test
    void testSetName() throws SQLException {
        clientService.setName(1L, "Jane Doe");

        verify(preparedStatement).setString(1, "Jane Doe");
        verify(preparedStatement).setLong(2, 1L);
        verify(preparedStatement).execute();
    }

    @Test
    void testDeleteById() throws SQLException {
        clientService.deleteById(1L);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(preparedStatement, times(3)).setLong(captor.capture(), captor.getValue());
        verify(preparedStatement, times(3)).execute();
    }

    @Test
    void testListAll() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("John Doe");

        List<Client> clients = clientService.listAll();

        assertEquals(1, clients.size());
        assertEquals("John Doe", clients.get(0).getName());
    }

    @Test
    void testListAllEmpty() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        List<Client> clients = clientService.listAll();

        assertTrue(clients.isEmpty());
    }
}
