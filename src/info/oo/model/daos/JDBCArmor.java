package info.oo.model.daos;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.services.AuthService;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCArmor implements ArmorDAO {

    private static String INSERT_ARMOR = "INSERT INTO table_armor(name, guardian_class, type, rarity, status, " +
            "status_masterprice, element, cod_user) values(?,?,?,?,?,?,?,?)";
    private static String INSERT_ARMOR_ATTRIBUTE = "INSERT INTO table_armor_attributes(cod_armor, cod_attributes) " +
            "values(?,?)";
    private static String CALL_TYPE = "call type_armor(?)";
    private static String CALL_TYPE_RARITY = "call rarity_type_armor(?, ?)";

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
        pstmt.setInt(8, armor.getCodUser());

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

    @Override
    public List<Armor> selectArmorByType(int id, String type) throws SQLException {

        List<Armor> armorList = new ArrayList<>();

        Connection conn = connectionsFactory.getConnection();

        CallableStatement cstmt = conn.prepareCall(CALL_TYPE);

        cstmt.setString(1, type);

        ResultSet rs = cstmt.executeQuery();

        while(rs.next()) {
            int id_attributes = rs.getInt("cod_attributes");
            int modility = rs.getInt("mobility");
            int resilience = rs.getInt("resilience");
            int recovery = rs.getInt("recovery");
            int dicipline = rs.getInt("dicipline");
            int intellect = rs.getInt("intellect");
            int strenght = rs.getInt("strenght");

            int id_armor = rs.getInt("cod_armor");
            String name = rs.getString("name");
            String guardian_class = rs.getString("guardian_class");
            String typeBd = rs.getString("type");
            String rarity = rs.getString("rarity");
            boolean status = rs.getBoolean("status");
            boolean masterprice = rs.getBoolean("status_masterprice");
            String element = rs.getString("Element");
            int cod_user = rs.getInt("cod_user");

            ArmorAttribute armorAttribute = new ArmorAttribute(id_attributes, modility, resilience,
                    recovery, dicipline, intellect, strenght);

            Armor armor = new Armor(id_armor, name, guardian_class, typeBd, rarity, status,
                                    masterprice, armorAttribute, element, cod_user);

            armorList.add(armor);
        }

        rs.close();
        cstmt.close();
        conn.close();

        return armorList;

    }

    @Override
    public List<Armor> selectArmorByTypeAndRarity(int id, String type, String rarity) throws SQLException {
        return null;
    }
}
