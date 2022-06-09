package fr.baillieul;

import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)

public class UserBean {
    @Inject
    private UserDAO userDAO;

    public boolean authent(User user){
        return userDAO.createUser(user);
    }

    public String login(User user){
        return userDAO.generateToken(user);
    }

    public boolean logout(User user){
        return userDAO.deleteToken(user);
    }

    public String checkToken(String token){
        return userDAO.getToken(token);
    }

    public String getToken(User user){ //Non utilisé
        return userDAO.getTokenByUser(user);
    }
}
