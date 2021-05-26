package info.oo.model.daos;

import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.interfaces.AttributesDAO;

import java.sql.*;

public class JDBCAttributes implements AttributesDAO {

    private static String INSERT = "insert into table_attributes(mobility, resilience, recovery, dicipline, intellect, strenght) values(?,?,?,?,?,?)";
    private ConnectionsFactory connectionsFactory;

    public JDBCAttributes(ConnectionsFactory connectionsFactory) {
        this.connectionsFactory = connectionsFactory;
    }

    @Override
    public boolean createAttributesDAO(ArmorAttribute armorAttribute) throws SQLException {
        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        pstmt.setInt(1, armorAttribute.getMobility());
        pstmt.setInt(2, armorAttribute.getResilience());
        pstmt.setInt(3, armorAttribute.getRecovery());
        pstmt.setInt(4, armorAttribute.getDicipline());
        pstmt.setInt(5, armorAttribute.getIntellect());
        pstmt.setInt(6, armorAttribute.getStrenght());

        pstmt.executeUpdate();

        ResultSet rsId = pstmt.getGeneratedKeys();
        rsId.next();

        int id = rsId.getInt(1);

        rsId.close();
        pstmt.close();

        armorAttribute.setId(id);

        return true;
    }
}
