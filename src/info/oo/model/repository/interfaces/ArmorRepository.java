package info.oo.model.repository.interfaces;

import info.oo.model.Armor;
import info.oo.model.ResultArmor;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface ArmorRepository {
    boolean register(Armor armor) throws SQLException;

    boolean search(int id, String guardianClass) throws SQLException;

    ObservableList<Armor> organizeByRarity(String rarity);

    ObservableList<Armor> organizeByType(String type);

    boolean resultCalculateArmors(Armor exotic,
                                  boolean powerfulFriends,
                                  boolean radiantLight,
                                  boolean stasis);

    ObservableList<Armor[]> getResultArmors();

    boolean update(Armor armor) throws SQLException;
}
