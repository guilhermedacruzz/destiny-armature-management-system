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

    private ObservableList<Armor[]> results;

    public ArmorRepositoryImpl(ArmorDAO armorDAO, AttributesDAO attributesDAO) {
        this.armorDAO = armorDAO;
        this.attributesDAO = attributesDAO;
        results = FXCollections.observableArrayList();
    }

    @Override
    public boolean insert(Armor armor) throws SQLException {
        return armorDAO.insert(armor);
    }

    @Override
    public ObservableList<Armor> select(int id, String guardianClass) throws SQLException {

        ObservableList<Armor> observableList = FXCollections.observableArrayList();

        List<Armor> armorAllList = armorDAO.select(id, guardianClass);

        for(Armor armor: armorAllList) {
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectByIdArmor(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        observableList.addAll(armorAllList);

        return observableList;
    }

    @Override
    public ObservableList<Armor> selectByType(int id, String guardianClass, String type) throws SQLException{
        ObservableList<Armor> observableList = FXCollections.observableArrayList();

        List<Armor> armorAllList = armorDAO.selectByType(id, guardianClass, type);

        for(Armor armor: armorAllList) {
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectByIdArmor(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        observableList.addAll(armorAllList);

        return observableList;
    }

    @Override
    public ObservableList<Armor> selectByRarity(int id, String guardianClass, String rarity) throws SQLException{
        ObservableList<Armor> observableList = FXCollections.observableArrayList();

        List<Armor> armorAllList = armorDAO.selectByRarity(id, guardianClass, rarity);

        for(Armor armor: armorAllList) {
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectByIdArmor(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        observableList.addAll(armorAllList);

        return observableList;
    }

    @Override
    public boolean update(Armor armor) throws SQLException {
        return armorDAO.update(armor);
    }
}
