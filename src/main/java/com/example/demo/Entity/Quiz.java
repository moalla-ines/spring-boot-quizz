package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquiz")
    private Integer id;
    private String titre_quiz;
    private String description;
    private Integer nb_questions;



    @ManyToOne
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    private Categorie categorie;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(Integer id,String titre_quiz,String description, Integer nb_questions, Categorie categorie, List<Question> questions) {
        this.id = id;
        this.titre_quiz = titre_quiz;
        this.description = description;
        this.nb_questions = nb_questions;
        this.categorie = categorie;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "idquiz=" + id +
                ", categorie=" + categorie +
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
}
