package fr.baillieul;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;

@Stateless
public class Appli {

    private static List<Departement> departements = new ArrayList<>();

    public Departement getById(String idDep){
        Departement nomDepartement = null;
        Optional<Departement> nomDepartementTemp =  departements
            .stream()
            .filter(d -> d.getNomDepartement().equals(idDep))
            .findFirst();
        if (nomDepartementTemp.isPresent()) {
            nomDepartement = nomDepartementTemp.get();
        }
        return nomDepartement;
    }
    public List<Departement> getAllDepartements() {
        return departements;
    }

    public void addDepartements(Departement departement) {
        departements.add(departement);
    }
    
    public void deleteDepartementByName(String name) {
        List<Departement> ListTemp = new ArrayList<>();
        for (Departement dep : departements) {
            if (!dep.getNomDepartement().equals(name)) {
                ListTemp.add(dep);
            }
        }
        departements.clear();
        departements.addAll(ListTemp);
    }

    public void updateDepartementName(Departement oldDepartementName) {

        String name = oldDepartementName.getNomDepartement();
        String id = oldDepartementName.getidDepartement();
        List<Departement> ListTemp = new ArrayList<>();

        for (Departement depa : departements) {
            if (depa.getNomDepartement().equals(id)) {
                depa.setNomDepartement(name);
            }
            ListTemp.add(depa);
        }
        departements.clear();
        departements.addAll(ListTemp);
    }
}