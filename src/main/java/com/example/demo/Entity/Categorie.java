package com.example.demo.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcategorie;
    private String titre_categorie;

    public Categorie(Integer idcategorie, String titre_categorie) {
        this.idcategorie = idcategorie;
        this.titre_categorie = titre_categorie;
    }

    public Categorie(String titre_categorie) {
        this.titre_categorie = titre_categorie;
    }

    public Categorie() {
    }

    public Integer getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getTitre_categorie() {
        return titre_categorie;
    }

    public void setTitre_categorie(String titre_categorie) {
        this.titre_categorie = titre_categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idcategorie=" + idcategorie +
                ", titre_categorie='" + titre_categorie + '\'' +
                '}';
    }
}