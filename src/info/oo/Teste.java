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

        boolean a = authService.login("rofdfdadsdffdsfdssfger", "1234");
        System.out.println(a);

        User user =  authService.getLogged();

        System.out.println(user.toString());

    }
}