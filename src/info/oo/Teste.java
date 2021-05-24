package info.oo;

import info.oo.model.ConnectionsFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Teste {

    public static void main(String[] args) throws SQLException {
        ConnectionsFactory connectionsFactory =  new ConnectionsFactory();
        Connection conn = connectionsFactory.getConnection();

        String sql = "SELECT * FROM table_armor";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){

            String name = rs.getString("name");

            System.out.println(name);
        }

        rs.close();
        pstmt.close();
        conn.close();

    }
}