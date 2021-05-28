package info.oo;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCArmor;
import info.oo.model.daos.JDBCAttributes;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;

import java.sql.SQLException;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws SQLException {
        ConnectionsFactory connectionsFactory = new ConnectionsFactory();

        ArmorDAO armorDAO = new JDBCArmor(connectionsFactory);

        List<Armor> armorList = armorDAO.selectArmor(1);

        System.out.println(armorList);

        AttributesDAO attributesDAO = new JDBCAttributes(connectionsFactory);

        List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armorList.get(armorList.size()-1).getId());

        System.out.println(armorAttributeList.toString());

    }
}