package info.oo.model.daos.interfaces;

import info.oo.model.User;

import java.sql.SQLException;

public interface UserDAO {

    User login(String username, String password) throws SQLException;

    boolean signIn(User user) throws SQLException;
}
