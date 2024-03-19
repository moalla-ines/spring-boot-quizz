package com.example.demo.Entity;

public class Quiz {
private Integer id_quiz;
    private Integer id_categorie;

private String questions;

    public Quiz(Integer id_quiz, Integer id_categorie, String questions) {
        this.id_quiz = id_quiz;
        this.id_categorie = id_categorie;
        this.questions = questions;
    }

    public Quiz(Integer id_categorie, String questions) {
        this.id_categorie = id_categorie;
        this.questions = questions;
    }

    public Quiz() {
    }

    public Integer getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(Integer id_quiz) {
        this.id_quiz = id_quiz;
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
                "id_quiz=" + id_quiz +
                ", id_categorie=" + id_categorie +
                ", questions='" + questions + '\'' +
                '}';
    }
}
