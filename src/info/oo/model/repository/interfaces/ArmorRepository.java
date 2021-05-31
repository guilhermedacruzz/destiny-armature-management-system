package info.oo.model.repository.interfaces;

import info.oo.model.Armor;
import info.oo.model.ResultArmor;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface ArmorRepository {
    boolean register(Armor armor) throws SQLException;

    List<Armor> search(int id, String guardianClass) throws SQLException;

    ObservableList<Armor> organizeByRarity(List<Armor> armorList, String rarity);

    ObservableList<Armor> organizeByType(List<Armor> armorList, String type);

    ObservableList<ResultArmor> resultCalculateArmors(List<Armor> armorList, Armor exotic);
}
