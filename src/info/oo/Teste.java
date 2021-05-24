package info.oo;


import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCUser;
import info.oo.model.daos.interfaces.UserDAO;
import info.oo.services.AuthService;

import java.sql.SQLException;

public class Teste {

    public static void main(String[] args) throws SQLException {

        ConnectionsFactory connectionsFactory = new ConnectionsFactory();
        UserDAO userDAO = new JDBCUser(connectionsFactory);
        AuthService authService = new AuthService(userDAO);

        authService.signIn("asdf", "fghj", "qweffr", "qwer");

        authService.login("qweffr", "qwer");

        System.out.println(authService.getLogged().toString());
    }
}