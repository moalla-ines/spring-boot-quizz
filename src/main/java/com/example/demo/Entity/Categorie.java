package com.example.demo.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcategorie;
    private String titre_categorie;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quiz> quizzes;
    @OneToMany(mappedBy = "idNiveau", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Niveau> niveauList;

    public Categorie(Integer idcategorie, String titre_categorie, List<Quiz> quizzes, List<Niveau> niveauList) {
        this.idcategorie = idcategorie;
        this.titre_categorie = titre_categorie;
        this.quizzes = quizzes;
        this.niveauList = niveauList;
    }

    public Categorie(String titre_categorie, List<Niveau> niveauList) {
        this.titre_categorie = titre_categorie;
        this.niveauList = niveauList;
    }

    public Categorie() {
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
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

    public List<Niveau> getNiveauList() {
        return niveauList;
    }

    public void setNiveauList(List<Niveau> niveauList) {
        this.niveauList = niveauList;
    }
}