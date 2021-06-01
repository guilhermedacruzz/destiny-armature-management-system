package info.oo.model.repository;

import info.oo.model.ArmorAttribute;
import info.oo.model.daos.interfaces.AttributesDAO;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;

import java.sql.SQLException;

public class ArmorAttributesRepositoryImpl implements ArmorAttributesRepository {

    private AttributesDAO attributesDAO;

    public ArmorAttributesRepositoryImpl(AttributesDAO attributesDAO) {
        this.attributesDAO = attributesDAO;
    }

    @Override
    public boolean registerAttribute(ArmorAttribute armorAttribute) throws SQLException {
        return attributesDAO.createAttributesDAO(armorAttribute);
    }

    @Override
    public boolean updateAttribute(ArmorAttribute armorAttribute) throws SQLException {
        return attributesDAO.updateAttributes(armorAttribute);
    }
}
