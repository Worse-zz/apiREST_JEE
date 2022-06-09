package fr.baillieul;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Departement implements Serializable {
    public Departement() {
    }
    
    public Departement(String nomDepartement, String idDepartement, String prefecture, String region) {
        this.nomDepartement = nomDepartement;
        this.idDepartement = idDepartement;
        this.prefecture = prefecture;
        this.region = region;
    }
    
    @Id @GeneratedValue
    private int id;

    @Column
    private String idDepartement;

    @Column
    private String nomDepartement;
    
    @Column
    private String prefecture;

    @Column
    private String region;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public String getidDepartement() {
        return idDepartement;
    }

    public void setidDepartement(String idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }   
    @Override
    public String toString() {
        return "Departement{" +
                "idDepartement=" + idDepartement +
                ", nomDepartement='" + nomDepartement + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}