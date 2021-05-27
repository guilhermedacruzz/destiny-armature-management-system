package info.oo.model.daos;

import info.oo.model.Armor;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.interfaces.ArmorDAO;

import java.sql.*;

public class JDBCArmor implements ArmorDAO {

    private static String INSERT_ARMOR = "INSERT INTO table_armor(name, guardian_class, type, rarity, status, " +
            "status_masterprice, element) values(?,?,?,?,?,?,?)";

    private static String INSERT_ARMOR_ATTRIBUTE = "INSERT INTO table_armor_attributes(cod_armor, cod_attributes) " +
            "values(?,?)";

    private ConnectionsFactory connectionsFactory;

    public JDBCArmor(ConnectionsFactory connectionsFactory) {
        this.connectionsFactory = connectionsFactory;
    }

    @Override
    public boolean createArmor(Armor armor) throws SQLException {
        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT_ARMOR, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, armor.getName());
        pstmt.setString(2, armor.getGuardianClass());
        pstmt.setString(3, armor.getType());
        pstmt.setString(4, armor.getRarity());
        pstmt.setBoolean(5, armor.isStatus());
        pstmt.setBoolean(6, armor.isStatusMasterprice());
        pstmt.setString(7, armor.getElement());

        pstmt.executeUpdate();


        ResultSet rsId = pstmt.getGeneratedKeys();

        rsId.next();

        int id = rsId.getInt(1);

        rsId.close();
        pstmt.close();

        armor.setId(id);

        pstmt = conn.prepareStatement(INSERT_ARMOR_ATTRIBUTE);

        pstmt.setInt(1, armor.getId());
        pstmt.setInt(2, armor.getArmorAttribute().getId());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return true;
    }
}
