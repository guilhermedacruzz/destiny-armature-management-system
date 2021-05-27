package info.oo;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCArmor;
import info.oo.model.daos.JDBCAttributes;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;

import java.sql.SQLException;


public class Teste {

    public static void main(String[] args) throws SQLException {
        ConnectionsFactory connectionsFactory = new ConnectionsFactory();

        AttributesDAO attributesDAO = new JDBCAttributes(connectionsFactory);
        ArmorDAO armorDAO = new JDBCArmor(connectionsFactory);

        ArmorAttribute armorAttribute = new ArmorAttribute(9, 9, 22, 4, 2, 4);
        attributesDAO.createAttributesDAO(armorAttribute);

        System.out.println(armorAttribute.toString());

        Armor armor = new Armor("Armsnd", "arc", "tipo", "rarity", true, false, armorAttribute, "solar", 1);

        armorDAO.createArmor(armor);

        System.out.println(armor.toString());
    }
}