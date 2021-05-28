package info.oo.model.daos.interfaces;

import info.oo.model.Armor;

import java.sql.SQLException;
import java.util.List;

public interface ArmorDAO {
    boolean createArmor(Armor armor) throws SQLException;

    List<Armor> selectArmorByType(int id, String type) throws SQLException;

    List<Armor> selectArmorByTypeAndRarity(int id, String type, String rarity) throws SQLException;
}
