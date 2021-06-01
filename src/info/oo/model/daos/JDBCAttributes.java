package info.oo.model.daos;

import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.interfaces.AttributesDAO;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAttributes implements AttributesDAO {

    private static final String INSERT = "insert into table_attributes(mobility, resilience, recovery, dicipline, " +
            "intellect, strenght) values(?,?,?,?,?,?)";
    private static final String SELECT_ID_ARMOR = "select cod_attributes from table_armor_attributes where cod_armor=?";
    private static final String SELECT_ID = "select * from table_attributes where cod_attributes=?";
    private static final String UPDATE = "update table_attributes set mobility=?, resilience=?, recovery=?," +
                                                "dicipline=?, intellect=?, strenght=? where cod_attributes=?";

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
        conn.close();

        armorAttribute.setId(id);

        return true;
    }

    @Override
    public List<ArmorAttribute> selectAttributes(int id) throws SQLException {
        List<ArmorAttribute> armorAttributeList = new ArrayList<>();

        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(SELECT_ID_ARMOR);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            int id_attribute = rs.getInt("cod_attributes");

            ArmorAttribute armorAttribute = selectIdAttributes(id_attribute);

            armorAttributeList.add(armorAttribute);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return armorAttributeList;
    }

    @Override
    public ArmorAttribute selectIdAttributes(int id) throws SQLException {

        ArmorAttribute armorAttribute = null;

        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(SELECT_ID);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            int id_attributes = rs.getInt("cod_attributes");
            int mobility = rs.getInt("mobility");
            int resilience = rs.getInt("resilience");
            int recovery = rs.getInt("recovery");
            int dicipline = rs.getInt("dicipline");
            int intellect = rs.getInt("intellect");
            int strenght = rs.getInt("strenght");

            armorAttribute = new ArmorAttribute(id_attributes, mobility, resilience,
                                                recovery, dicipline, intellect, strenght);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return  armorAttribute;
    }

    @Override
    public boolean updateAttributes(ArmorAttribute armorAttribute) throws SQLException {
        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setInt(1, armorAttribute.getMobility());
        pstmt.setInt(2, armorAttribute.getResilience());
        pstmt.setInt(3, armorAttribute.getRecovery());

        pstmt.setInt(4, armorAttribute.getDicipline());
        pstmt.setInt(5, armorAttribute.getIntellect());
        pstmt.setInt(6, armorAttribute.getStrenght());
        pstmt.setInt(7, armorAttribute.getId());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

}
