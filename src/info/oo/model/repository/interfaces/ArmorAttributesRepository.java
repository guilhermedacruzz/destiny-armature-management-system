package info.oo.model.repository.interfaces;

import info.oo.model.ArmorAttribute;
import java.sql.SQLException;

public interface ArmorAttributesRepository {
    boolean insert(ArmorAttribute armorAttribute) throws SQLException;

    boolean update(ArmorAttribute armorAttribute) throws SQLException;
}
