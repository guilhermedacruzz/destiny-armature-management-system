package info.oo.model.repository.interfaces;

import info.oo.model.ArmorAttribute;
import java.sql.SQLException;

public interface ArmorAttributesRepository {
    boolean registerAttribute(ArmorAttribute armorAttribute) throws SQLException;

    boolean updateAttribute(ArmorAttribute armorAttribute) throws SQLException;
}
