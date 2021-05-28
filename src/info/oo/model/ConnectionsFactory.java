package info.oo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionsFactory {
    private static final Integer MAX_CON = 5;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sgda7";
    private static final String USER = "cruz";
    private static final String PASSWORD = "cruz";

    private Connection[] connections;

    public ConnectionsFactory() {
        this.connections = new Connection[MAX_CON];
    }

    public Connection getConnection() throws SQLException {
        for(int i = 0; i < connections.length; i++) {
            if(connections[i] ==  null || connections[i].isClosed()) {
                connections[i] = DriverManager.getConnection(URL, USER, PASSWORD);
                return connections[i];
            }
        }

        throw new SQLException("No connections");
    }
}
