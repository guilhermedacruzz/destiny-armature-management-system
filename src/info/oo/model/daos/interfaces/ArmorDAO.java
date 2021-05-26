package info.oo.model.daos.interfaces;

import info.oo.model.Armor;

import java.sql.SQLException;

public interface ArmorDAO {
    boolean createArmor(Armor armor) throws SQLException;
}
