package info.oo.model.daos.interfaces;

import info.oo.model.Armor;

import java.sql.SQLException;
import java.util.List;

public interface ArmorDAO {
    boolean createArmor(Armor armor) throws SQLException;

    List selectArmor(int id) throws SQLException;

}