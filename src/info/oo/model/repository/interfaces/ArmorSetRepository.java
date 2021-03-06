package info.oo.model.repository.interfaces;

import info.oo.model.Armor;
import info.oo.model.ArmorSet;
import info.oo.model.Inventory;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ArmorSetRepository {
    ObservableList<ArmorSet> calculate(Armor exotic, boolean powerfulFriends, boolean radiantLight, boolean stasis, Inventory inventory) throws SQLException;
}
