package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquiz;


    @JoinColumn(name = "idcategorie")
    public Integer idcategorie;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(Integer idquiz, Integer idcategorie, List<Question> questions) {
        this.idquiz = idquiz;
        this.idcategorie = idcategorie;
        this.questions = questions;
    }

    public Integer getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(Integer id) {
        this.idquiz = idquiz;
    }

    public Integer getId_categorie() {
        return idcategorie;
    }

    public void setId_categorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quiz{idquiz=").append(idquiz);
        sb.append(", idcategorie=").append(idcategorie);
        sb.append(", questions=[");
        for (Question question : questions) {
            sb.append(question.getText()).append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}