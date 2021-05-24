package info.oo;


import info.oo.model.ConnectionsFactory;
import info.oo.model.User;
import info.oo.model.daos.JDBCUser;
import info.oo.model.daos.interfaces.UserDAO;
import info.oo.services.AuthService;

import java.sql.SQLException;

public class Teste {

    public static void main(String[] args) throws SQLException {

        ConnectionsFactory connectionsFactory = new ConnectionsFactory();
        UserDAO userDAO = new JDBCUser(connectionsFactory);
        AuthService authService = new AuthService(userDAO);

        User user = new User("asdf", "fghj", "qwer", "qwer");

        authService.signIn(user);

        authService.login("qwer", "qwer");

        System.out.println(authService.getLogged().toString());
    }
}