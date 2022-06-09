package fr.baillieul;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;


public class DepartementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public List<Departement> getAllDepartements(){
        return entityManager.createQuery("SELECT dp FROM Departement dp", Departement.class).getResultList();
    }

    public Departement getDepartement(String idDepartement) {
        TypedQuery<Departement> query = entityManager.createQuery(
            "SELECT dp FROM Departement dp WHERE dp.idDepartement = (?1)", Departement.class);
        try{
            Departement bddDepartement = query
                .setParameter(1, idDepartement)
                .getSingleResult();
            return bddDepartement;
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return null;
        }
    }

    public boolean addDepartements(Departement departement){
        try{
            userTransaction.begin();
            entityManager.persist(departement);
            userTransaction.commit();
            return true;
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean delDepartements(String idDepartement){
        try{           
            userTransaction.begin();
            entityManager.remove(getDepartement(idDepartement));
            userTransaction.commit();
            return true;
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean updateDepartementName(Departement departement){
        TypedQuery<Departement> query = entityManager.createQuery(
            "SELECT dp FROM Departement dp WHERE dp.idDepartement = (?1)", Departement.class);
        try{
            Departement bddDepartement = query.setParameter(1, departement.getidDepartement()).getSingleResult();
            if (bddDepartement == null){
                Logger.getGlobal().log(Level.SEVERE, "Departement non existant");
            }
            else 
            {
                bddDepartement.setNomDepartement(departement.getNomDepartement());
                if (departement.getPrefecture() != null) {bddDepartement.setPrefecture(departement.getPrefecture());}
                if (departement.getRegion() != null) {bddDepartement.setRegion(departement.getRegion());}
                userTransaction.begin();
                entityManager.merge(bddDepartement);
                userTransaction.commit();
            }
            return true;
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

}
