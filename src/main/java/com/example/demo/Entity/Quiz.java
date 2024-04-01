package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquiz;

    @Column(name = "idcategorie")
    private Integer idCategorie;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;
    public Quiz() {
    }

    public Quiz(Integer idquiz, Integer idCategorie, List<Question> questions) {
        this.idquiz = idquiz;
        this.idCategorie = idCategorie;
        this.questions = questions;
    }

    public Integer getIdquiz() {
        return idquiz;
    }

    public void setId(Integer idquiz) {
        this.idquiz = idquiz;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
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
                ", idCategorie=" + idCategorie +
                ", questions=" + questions +
                '}';
    }
}
