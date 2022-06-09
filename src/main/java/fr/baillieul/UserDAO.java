package fr.baillieul;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import java.security.MessageDigest;
import java.util.UUID;

public class UserDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;


    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        String stringHash = new String(messageDigest.digest());
        return (stringHash);
    }


    public boolean createUser(User user) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :mail", User.class);
        try {
            User bddUser = query
                    .setParameter("mail", user.getEmail()).getSingleResult();
        } catch (NoResultException e) {
            try {
                user.setPassword(encryptPassword(user.getPassword()));
                userTransaction.begin();
                entityManager.persist(user);
                userTransaction.commit();
                return true;
            } catch (Exception exception) {
                Logger.getGlobal().log(Level.SEVERE, "JPA error" + exception.getMessage());
            }
            Logger.getGlobal().log(Level.SEVERE, "Un compte existe déjà pour cet email");
            return false;
        }
        return false;
    }

    public String generateToken(User user) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.email = :mail AND u.password = :mdp", User.class);
        try{
            User bddUser = query
                .setParameter("mail", user.getEmail())
                .setParameter("mdp", encryptPassword(user.getPassword()))
                .getSingleResult();
            if (bddUser == null){
                Logger.getGlobal().log(Level.SEVERE, "Email ou mot de passe invalide");
                return null;
            }
            else 
            {
                UUID uuid = UUID.randomUUID();
                bddUser.setToken(uuid.toString());
                userTransaction.begin();
                entityManager.merge(bddUser);
                userTransaction.commit();
                return uuid.toString();
            }
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return null;
        }
    }

    public boolean deleteToken(User user) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.email = :mail AND u.password = :mdp", User.class);
        try{
            User bddUser = query
                .setParameter("mail", user.getEmail())
                .setParameter("mdp", encryptPassword(user.getPassword()))
                .getSingleResult();
            if (bddUser == null){
                Logger.getGlobal().log(Level.SEVERE, "Email ou mot de passe invalide");
                return false;
            }
            else 
            {
                bddUser.setToken(null);
                userTransaction.begin();
                entityManager.merge(bddUser);
                userTransaction.commit();
                return true;
            }
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }   

    public String getToken(String token){
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.token = :token", User.class);
        try{
            User bddUser = query
                .setParameter("token", token)    
                .getSingleResult();
            if (bddUser == null){
                Logger.getGlobal().log(Level.SEVERE, "Token non assigné, merci de vous authentifier et de vous logger");
            }
            return bddUser.getToken();
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return null;
        }

    }

    public String getTokenByUser(User user){ //Non utilisé
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.token = :id", User.class);
        try{
            User bddUser = query
                .setParameter("id", user.getId())
                .getSingleResult();
            return bddUser.getToken();
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return null;
        }

    }

}
