package info.oo.services;

import info.oo.model.User;
import info.oo.model.daos.interfaces.UserDAO;

import java.sql.SQLException;

public class AuthService {

    private User logged;
    private UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) throws SQLException {
        logged = userDAO.login(username, password);
        return logged!=null;
    }

    public boolean signIn(User user) throws SQLException {
        return userDAO.signIn(user);
    }

    public User getLogged() {
        return logged;
    }
}
