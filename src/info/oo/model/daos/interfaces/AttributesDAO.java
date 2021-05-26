package info.oo.model.daos.interfaces;

import info.oo.model.ArmorAttribute;

import java.sql.SQLException;

public interface AttributesDAO {
    boolean createAttributesDAO(ArmorAttribute armorAttribute) throws SQLException;
}
