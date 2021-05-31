package info.oo.model.repository;

import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ResultArmor;
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

    public ArmorRepositoryImpl(ArmorDAO armorDAO, AttributesDAO attributesDAO) {
        this.armorDAO = armorDAO;
        this.attributesDAO = attributesDAO;
    }

    @Override
    public boolean register(Armor armor) throws SQLException {
        return armorDAO.createArmor(armor);
    }

    @Override
    public List<Armor> search(int id, String guardianClass) throws SQLException {

        List<Armor> armorList = armorDAO.selectArmor(id, guardianClass);

        for(Armor armor: armorList) {
            List<ArmorAttribute> armorAttributeList = attributesDAO.selectAttributes(armor.getId());
            armor.setArmorAttribute(armorAttributeList.get(0));
        }

        return armorList;
    }

    public ObservableList<Armor> organizeByType(List<Armor> armorList, String type) {
        ObservableList<Armor> armorObservableList = FXCollections.observableArrayList();

        List<Armor> armorListByType = new ArrayList<>();

        for(Armor armor: armorList)
            if(armor.getType().equals(type) && armor.getRarity().equals("Lendário"))
                armorListByType.add(armor);

        armorObservableList.addAll(armorListByType);

        return FXCollections.unmodifiableObservableList(armorObservableList);
    }

    @Override
    public ObservableList<ResultArmor> resultCalculateArmors(List<Armor> armorList, Armor exotic,
                                                             boolean powerfulFriends,
                                                             boolean radiantLight,
                                                             boolean stasis) {

        ObservableList<Armor> helmets = organizeByType(armorList, "Capacete");
        ObservableList<Armor> arms = organizeByType(armorList, "Manopla");
        ObservableList<Armor> chests = organizeByType(armorList, "Armadura de Torso");
        ObservableList<Armor> boots = organizeByType(armorList, "Armadura de Perna");
        ObservableList<Armor> classItens = organizeByType(armorList, "Item de Classe");

        ObservableList<ResultArmor> resultArmors = FXCollections.observableArrayList();

        for(Armor helmet: helmets) {
            helmet = checkExotic(exotic, helmet);
            for(Armor arm: arms) {
                arm = checkExotic(exotic, arm);
                for(Armor chest: chests) {
                    chest = checkExotic(exotic, chest);
                    for(Armor boot: boots) {
                        boot = checkExotic(exotic, boot);
                        for(Armor classItem: classItens) {
                            ResultArmor resultArmor = new ResultArmor(helmet, arm, chest, boot, classItem,
                                                          powerfulFriends, radiantLight, stasis);
                            resultArmors.add(resultArmor);
                        }
                    }
                }
            }
        }

        return resultArmors;
    }

    private Armor checkExotic(Armor exotic, Armor armor) {
        if(armor.getType().equals(exotic.getType()))
            return exotic;
        return armor;
    }


    public ObservableList<Armor> organizeByRarity(List<Armor> armorList, String rarity) {
        ObservableList<Armor> armorObservableList = FXCollections.observableArrayList();

        List<Armor> armorListByRarity = new ArrayList<>();

        for(Armor armor: armorList)
            if(armor.getRarity().equals(rarity))
                armorListByRarity.add(armor);

        armorObservableList.addAll(armorListByRarity);

        return FXCollections.unmodifiableObservableList(armorObservableList);
    }
}
