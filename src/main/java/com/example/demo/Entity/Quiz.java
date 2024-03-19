package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquiz;
    private Integer id_categorie;

    private String questions;

    public Quiz(Integer idquiz, Integer id_categorie, String questions) {
        this.idquiz = idquiz;
        this.id_categorie = id_categorie;
        this.questions = questions;
    }

    public Quiz(Integer id_categorie, String questions) {
        this.id_categorie = id_categorie;
        this.questions = questions;
    }

    public Quiz() {
    }

    public Integer getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(Integer idquiz) {
        this.idquiz = idquiz;
    }

    public Integer getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idquiz=" + idquiz +
                ", id_categorie=" + id_categorie +
                ", questions='" + questions + '\'' +
                '}';
    }
}

