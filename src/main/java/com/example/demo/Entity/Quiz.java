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
    @JoinColumn(name = "idNiveau")
    private Niveau niveau;
    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizHistory> quizHistoryList;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public List<QuizHistory> getQuizHistoryList() {
        return quizHistoryList;
    }

    public void setQuizHistoryList(List<QuizHistory> quizHistoryList) {
        this.quizHistoryList = quizHistoryList;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
