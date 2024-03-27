package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JoinColumn(name = "idcategorie")
    public Integer idcategorie;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(Integer id, Integer idcategorie, List<Question> questions) {
        this.id = id;
        this.idcategorie = idcategorie;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_categorie() {
        return idcategorie;
    }

    public void setId_categorie(Integer id_categorie) {
        this.idcategorie = id_categorie;
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
        sb.append("Quiz{id=").append(id);
        sb.append(", id_categorie=").append(idcategorie);
        sb.append(", questions=[");
        for (Question question : questions) {
            sb.append(question.getText()).append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}