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
    @OneToMany(mappedBy = "idcategorie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quiz> quizzes;

    public Categorie(Integer idcategorie, String titre_categorie, List<Quiz> quizzes) {
        this.idcategorie = idcategorie;
        this.titre_categorie = titre_categorie;
        this.quizzes = quizzes;
    }

    public Categorie(String titre_categorie) {
        this.titre_categorie = titre_categorie;
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
}