package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquestion;
    private String text;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer indiceoptionCorrecte;
    @ManyToOne
    @JoinColumn(name = "idquiz")
 @JsonBackReference// Indique que cette propriété est gérée par l'autre côté de la relation
    private Quiz quiz;

    public Integer getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(Integer idquestion) {
        this.idquestion = idquestion;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Integer getIndiceoptionCorrecte() {
        return indiceoptionCorrecte;
    }

    public void setIndiceoptionCorrecte(Integer indiceoptionCorrecte) {
        this.indiceoptionCorrecte = indiceoptionCorrecte;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question(Integer idquestion, String text, String option1, String option2, String option3, String option4, Integer indiceoptionCorrecte, Quiz quiz) {
        this.idquestion = idquestion;
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.indiceoptionCorrecte = indiceoptionCorrecte;
        this.quiz = quiz;
    }

    public Question() {
    }

    @Override
    public String toString() {
        return "Question{" +
                "idquestion=" + idquestion +
                ", text='" + text + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", indiceoptionCorrecte=" + indiceoptionCorrecte +
                ", quiz=" + quiz +
                '}';
    }
}

