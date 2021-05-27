package info.oo.model.repository.interfaces;

import info.oo.model.Armor;

import java.sql.SQLException;

public interface ArmorRepository {
    boolean register(Armor armor) throws SQLException;
}
