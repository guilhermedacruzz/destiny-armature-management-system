package info.oo.model.daos;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.interfaces.ArmorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCArmor implements ArmorDAO {

    private static final String INSERT_ARMOR = "INSERT INTO table_armor(name, guardian_class, type, rarity, status, status_masterprice, element, cod_user) values(?,?,?,?,?,?,?,?)";
    private static final String INSERT_ARMOR_ATTRIBUTE = "INSERT INTO table_armor_attributes(cod_armor, cod_attributes) values(?,?)";
    private static final String SELECT = "select * from table_armor where cod_user=? and guardian_class=? and status=1";
    private static final String SELECT_TYPE = "select * from table_armor where cod_user=? and guardian_class=? and rarity=\"Lendário\" and type=? and status=1";
    private static final String SELECT_RARITY = "select * from table_armor where cod_user=? and guardian_class=? and rarity=? and status=1";
    private static final String UPDATE = "update table_armor set name=?, guardian_class=?, type=?, rarity=?, status=?, status_masterprice=?, element=? where cod_armor=?";
    private static final String DELETE = "update table_armor set status=? where cod_armor=?";

    private ConnectionsFactory connectionsFactory;

    public JDBCArmor(ConnectionsFactory connectionsFactory) {
        this.connectionsFactory = connectionsFactory;
    }

    @Override
    public boolean insert(Armor armor) throws SQLException {
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
    public boolean update(Armor armor) throws SQLException {
        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(UPDATE);

        pstmt.setString(1, armor.getName());
        pstmt.setString(2, armor.getGuardianClass());
        pstmt.setString(3, armor.getType());
        pstmt.setString(4, armor.getRarity());
        pstmt.setBoolean(5, armor.isStatus());
        pstmt.setBoolean(6, armor.isStatusMasterprice());
        pstmt.setString(7, armor.getElement());
        pstmt.setInt(8, armor.getId());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

    @Override
    public List select(int id, String guardianClass) throws SQLException {
        List<Armor> armorList = new ArrayList<>();

        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(SELECT);

        pstmt.setInt(1, id);
        pstmt.setString(2, guardianClass);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            int id_armor = rs.getInt("cod_armor");
            String name = rs.getString("name");
            String guardian_class = rs.getString("guardian_class");
            String typeBd = rs.getString("type");
            String rarity = rs.getString("rarity");
            boolean status = rs.getBoolean("status");
            boolean masterprice = rs.getBoolean("status_masterprice");
            String element = rs.getString("element");
            int cod_user = rs.getInt("cod_user");

            Armor armor = new Armor(id_armor, name, guardian_class, typeBd, rarity, status,
                    masterprice, null, element, cod_user);

            armorList.add(armor);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return armorList;
    }

    @Override
    public List<Armor> selectByType(int id, String guardianClass, String type) throws SQLException {
        List<Armor> armorList = new ArrayList<>();

        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(SELECT_TYPE);

        pstmt.setInt(1, id);
        pstmt.setString(2, guardianClass);
        pstmt.setString(3, type);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            int id_armor = rs.getInt("cod_armor");
            String name = rs.getString("name");
            String guardian_class = rs.getString("guardian_class");
            String typeBd = rs.getString("type");
            String rarityBd = rs.getString("rarity");
            boolean status = rs.getBoolean("status");
            boolean masterprice = rs.getBoolean("status_masterprice");
            String element = rs.getString("element");
            int cod_user = rs.getInt("cod_user");

            Armor armor = new Armor(id_armor, name, guardian_class, typeBd, rarityBd, status,
                    masterprice, null, element, cod_user);

            armorList.add(armor);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return armorList;
    }

    @Override
    public List<Armor> selectByRarity(int id, String guardianClass, String rarity) throws SQLException {
        List<Armor> armorList = new ArrayList<>();

        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(SELECT_RARITY);

        pstmt.setInt(1, id);
        pstmt.setString(2, guardianClass);
        pstmt.setString(3, rarity);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            int id_armor = rs.getInt("cod_armor");
            String name = rs.getString("name");
            String guardian_class = rs.getString("guardian_class");
            String typeBd = rs.getString("type");
            String rarityBd = rs.getString("rarity");
            boolean status = rs.getBoolean("status");
            boolean masterprice = rs.getBoolean("status_masterprice");
            String element = rs.getString("element");
            int cod_user = rs.getInt("cod_user");

            Armor armor = new Armor(id_armor, name, guardian_class, typeBd, rarityBd, status,
                    masterprice, null, element, cod_user);

            armorList.add(armor);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return armorList;
    }

    @Override
    public boolean delete(Armor armor) throws SQLException {
        Connection conn = connectionsFactory.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(DELETE);

        pstmt.setBoolean(1, false);
        pstmt.setInt(2, armor.getId());

        int ret = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return ret == 1;
    }

}
