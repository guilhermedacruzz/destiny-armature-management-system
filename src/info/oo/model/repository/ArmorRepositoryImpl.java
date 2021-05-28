package info.oo.model.repository;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;
import info.oo.model.repository.interfaces.ArmorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ArmorRepositoryImpl implements ArmorRepository {

    private ArmorDAO armorDAO;
    private AttributesDAO attributesDAO;

    public ArmorRepositoryImpl(ArmorDAO armorDAO, AttributesDAO attributesDAO) {
        this.armorDAO = armorDAO;
        this.attributesDAO = attributesDAO;
    }

    @Override
    public boolean register(Armor armor) throws SQLException {
        return armorDAO.createArmor(armor);
    }

    @Override
    public List<Armor> search(int id) throws SQLException {
        List<Armor> armorList = armorDAO.selectArmor(id);

        for(Armor armor: armorList) {
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        return armorList;
    }
}
