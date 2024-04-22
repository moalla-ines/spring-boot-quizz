package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer indice_optionCorrecte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idquiz")
    @JsonIgnore  // Ignorer la sérialisation de cette propriété
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

    public Integer getIndice_optionCorrecte() {
        return indice_optionCorrecte;
    }

    public void setIndice_optionCorrecte(Integer indice_optionCorrecte) {
        this.indice_optionCorrecte = indice_optionCorrecte;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question(Integer idquestion, String text, String option1, String option2, String option3, String option4, Integer indice_optionCorrecte, Quiz quiz) {
        this.idquestion = idquestion;
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.indice_optionCorrecte = indice_optionCorrecte;
        this.quiz = quiz;
    }

    public Question() {
    }
}

