package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquiz")
    private Integer idquiz;

    private String titre_quiz;
    private String description;
    private Integer nb_questions;

    @ManyToOne
    @JoinColumn(name = "idNiveau")
    private Niveau niveau;
    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    @JsonBackReference// Indique que cette propriété est gérée par l'autre côté de la relation
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(String titre_quiz, String description, Integer nb_questions, Categorie categorie, Niveau niveau) {
        this.titre_quiz = titre_quiz;
        this.description = description;
        this.nb_questions = nb_questions;
        this.categorie = categorie;
        this.niveau = niveau;
    }


    public Integer getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(Integer idquiz) {
        this.idquiz = idquiz;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "idquiz=" + idquiz +
                ", categorie=" + categorie +
                ", niveau =" + niveau +
                ", questions=" + questions +
                '}';
    }


    public String getTitre_quiz() {
        return titre_quiz;
    }

    public void setTitre_quiz(String titre_quiz) {
        this.titre_quiz = titre_quiz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNb_questions() {
        return nb_questions;
    }

    public void setNb_questions(Integer nb_questions) {
        this.nb_questions = nb_questions;
    }



    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
