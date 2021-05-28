package info.oo;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCArmor;
import info.oo.model.daos.JDBCAttributes;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;
import info.oo.model.repository.ArmorRepositoryImpl;
import info.oo.model.repository.interfaces.ArmorRepository;

import java.sql.SQLException;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws SQLException {
        ConnectionsFactory connectionsFactory = new ConnectionsFactory();

        ArmorDAO armorDAO = new JDBCArmor(connectionsFactory);
        AttributesDAO attributesDAO = new JDBCAttributes(connectionsFactory);

        ArmorRepository armorRepository = new ArmorRepositoryImpl(armorDAO, attributesDAO);

        List<Armor> armorList = armorRepository.search(1);

        for (Armor armor: armorList)
            System.out.println(armor.toString());
    }
}