package info.oo.model.daos.interfaces;

import info.oo.model.ArmorAttribute;

import java.sql.SQLException;
import java.util.List;

public interface AttributesDAO {
    boolean insert(ArmorAttribute armorAttribute) throws SQLException;

    List<ArmorAttribute> selectByIdArmor(int id) throws SQLException;

    ArmorAttribute select(int id) throws SQLException;

    boolean update(ArmorAttribute armorAttribute) throws SQLException;
}
