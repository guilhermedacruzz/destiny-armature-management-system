package info.oo.model.repository;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;
import info.oo.model.repository.interfaces.ArmorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
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
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armor.getId());
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
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armor.getId());
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
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        observableList.addAll(armorAllList);

        return observableList;
    }

    @Override
    public boolean resultCalculateArmors(Armor exotic,
                                         boolean powerfulFriends,
                                         boolean radiantLight,
                                         boolean stasis) {

        ObservableList<ObservableList<Armor>> observableList = FXCollections.observableArrayList();

        /*
        if(!exotic.getType().equals("Capacete"))
            observableList.addAll(organizeByType("Capacete"));
        if(!exotic.getType().equals("Manopla"))
            observableList.addAll(organizeByType("Manopla"));
        if(!exotic.getType().equals("Armadura de Torso"))
            observableList.addAll(organizeByType("Armadura de Torso"));
        if(!exotic.getType().equals("Armadura de Perna"))
            observableList.addAll(organizeByType("Armadura de Perna"));
        if(!exotic.getType().equals("Item de Classe"))
            observableList.addAll(organizeByType("Item de Classe"));*/

        results.clear();

        Armor[] basic = new Armor[5];
        basic[4] = exotic;

        a(basic, observableList, 0);

        return true;
    }

    private void a(Armor[] resultArmors, ObservableList<ObservableList<Armor>> observableList, int index) {
        if(index == 4) {
            results.add(resultArmors.clone());
            return;
        }
        for(Armor armor: observableList.get(index)) {
            resultArmors[index] = armor;
            a(resultArmors, observableList, index + 1);
        }
    }

    @Override
    public ObservableList<Armor[]> getResultArmors() {
        return results;
    }

    @Override
    public boolean update(Armor armor) throws SQLException {
        return armorDAO.update(armor);
    }
}
