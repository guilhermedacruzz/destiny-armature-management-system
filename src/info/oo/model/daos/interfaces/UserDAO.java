package info.oo.model.daos.interfaces;

import info.oo.model.Inventory;
import info.oo.model.User;

import java.sql.SQLException;

public interface UserDAO {

    User login(String username, String secret) throws SQLException;

    boolean signIn(String name, String surname, String username, String secret) throws SQLException;

    boolean edit(User user) throws SQLException;
}
