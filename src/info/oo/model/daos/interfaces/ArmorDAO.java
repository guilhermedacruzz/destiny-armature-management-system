package info.oo.model.daos.interfaces;

import info.oo.model.Armor;

import java.sql.SQLException;
import java.util.List;

public interface ArmorDAO {
    boolean insert(Armor armor) throws SQLException;

    boolean update(Armor armor) throws SQLException;

    List<Armor> select(int id, String guardianClass) throws SQLException;

    List<Armor> selectByType(int id, String guardianClass, String type) throws SQLException;

    List<Armor> selectByRarity(int id, String guardianClass, String Rarity) throws SQLException;
}
