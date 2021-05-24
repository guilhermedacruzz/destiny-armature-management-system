package info.oo.model.daos;

import info.oo.model.ConnectionsFactory;
import info.oo.model.Inventory;
import info.oo.model.User;
import info.oo.model.daos.interfaces.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUser implements UserDAO {

    private ConnectionsFactory connectionsFactory;

    public JDBCUser(ConnectionsFactory connectionsFactory) {
        this.connectionsFactory = connectionsFactory;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        User user = null;

        Connection con = connectionsFactory.getConnection();

        String sql = "select * from table_user where username=? and secret=?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, username);
        pstm.setString(2, password);

        ResultSet rs = pstm.executeQuery();

        if(rs.next()) {
            int id = rs.getInt("cod_user");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String usernamebd = rs.getString("username");


            int lumen = rs.getInt("lumen");
            int legendaryFragments = rs.getInt("legendary_fragments");
            int ascendentFragments = rs.getInt("ascendent_fragments");
            int enhancementPrism = rs.getInt("enhancement_prism");
            int improvementCore = rs.getInt("improvement_core");
            int ehancementModule = rs.getInt("ehancement_module");

            Inventory inventory = new Inventory(lumen, legendaryFragments, ascendentFragments,
                    enhancementPrism, improvementCore, ehancementModule);

            user = new User(id, name, surname, username, inventory);
        }

        rs.close();
        pstm.close();
        con.close();

        return user;
    }
}
