package info.oo;

import info.oo.model.Armor;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCArmor;
import info.oo.model.daos.interfaces.ArmorDAO;

import java.sql.SQLException;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws SQLException {
        ConnectionsFactory connectionsFactory = new ConnectionsFactory();

        ArmorDAO armorDAO = new JDBCArmor(connectionsFactory);

        List<Armor> armorList = armorDAO.selectArmor(1);

        System.out.println(armorList.toString());
    }
}