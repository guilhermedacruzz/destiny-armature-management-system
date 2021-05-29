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

    private ObservableList<Armor> armorObservableList;

    private ArmorDAO armorDAO;
    private AttributesDAO attributesDAO;

    private List<Armor> helmets;
    private List<Armor> arms;
    private List<Armor> chest;
    private List<Armor> boots;
    private List<Armor> classItens;
    private List<Armor> exoticos;

    public ArmorRepositoryImpl(ArmorDAO armorDAO, AttributesDAO attributesDAO) {
        this.armorDAO = armorDAO;
        this.attributesDAO = attributesDAO;

        this.armorObservableList = FXCollections.observableArrayList();
    }

    @Override
    public boolean register(Armor armor) throws SQLException {
        return armorDAO.createArmor(armor);
    }

    @Override
    public ObservableList<Armor> search(int id) throws SQLException {

        this.armorObservableList.clear();

        List<Armor> armorList = armorDAO.selectArmor(id);

        for(Armor armor: armorList) {
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        this.armorObservableList.addAll(armorList);

        return FXCollections.unmodifiableObservableList(this.armorObservableList);
    }

    private List<Armor> organizeByType(List<Armor> armorList, String type) {
        List<Armor> armorListByType = new ArrayList<>();

        for(Armor armor: armorList)
            if(armor.getType().equals(type) && armor.getRarity().equals("Lendário"))
                armorListByType.add(armor);

        return armorListByType;
    }

    private List<Armor> organizeByRarity(List<Armor> armorList, String rarity) {
        List<Armor> armorListByRarity = new ArrayList<>();

        for(Armor armor: armorList)
            if(armor.getRarity().equals(rarity))
                armorListByRarity.add(armor);

        return armorListByRarity;
    }
}
