package fr.baillieul;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)

public class DepartementBean {
    @Inject
    private DepartementDAO departementDAO;

    public List<Departement> getAllDep() {
        return departementDAO.getAllDepartements();
    }

    public Departement getById(String numDep){
        return departementDAO.getDepartement(numDep);
    }   

    public boolean addDep(Departement departement) {
        return departementDAO.addDepartements(departement);
    }

    public void deleteDep(String idDepartement){
        departementDAO.delDepartements(idDepartement);
    }

    public void updateDepartementName(Departement departement){
        departementDAO.updateDepartementName(departement);
    }
}
