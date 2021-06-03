package info.oo.model.repository.interfaces;

import info.oo.model.Armor;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ArmorRepository {
    boolean insert(Armor armor) throws SQLException;

    ObservableList<Armor> select(int id, String guardianClass) throws SQLException;

    ObservableList<Armor> selectByRarity(int id, String guardianClass, String rarity) throws SQLException;

    ObservableList<Armor> selectByType(int id, String guardianClass, String type) throws SQLException;

    boolean resultCalculateArmors(Armor exotic,
                                  boolean powerfulFriends,
                                  boolean radiantLight,
                                  boolean stasis);

    ObservableList<Armor[]> getResultArmors();

    boolean update(Armor armor) throws SQLException;
}
