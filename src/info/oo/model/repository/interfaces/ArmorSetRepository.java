package info.oo.model.repository.interfaces;

import info.oo.model.Armor;
import info.oo.model.ArmorSet;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ArmorSetRepository {
    ObservableList<ArmorSet> calculate(Armor exotic, boolean powerfulFriends, boolean radiantLight, boolean stasis) throws SQLException;
}
