package info.oo.model.daos.interfaces;

import info.oo.model.ArmorAttribute;

import java.sql.SQLException;
import java.util.List;

public interface AttributesDAO {
    boolean createAttributesDAO(ArmorAttribute armorAttribute) throws SQLException;

    List<ArmorAttribute> selectAttributes(int id) throws SQLException;

    ArmorAttribute selectIdAttributes(int id) throws SQLException;

    boolean updateAttributes(ArmorAttribute armorAttribute) throws SQLException;
}
