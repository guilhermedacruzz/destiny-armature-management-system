package info.oo.model.repository;

import info.oo.model.Armor;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.repository.interfaces.ArmorRepository;

import java.sql.SQLException;

public class ArmorRepositoryImpl implements ArmorRepository {

    private ArmorDAO armorDAO;

    public ArmorRepositoryImpl(ArmorDAO armorDAO) {
        this.armorDAO = armorDAO;
    }

    @Override
    public boolean register(Armor armor) throws SQLException {
        return armorDAO.createArmor(armor);
    }
}
