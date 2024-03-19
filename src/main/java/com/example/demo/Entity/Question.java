package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquestion;
    private String text;
    private String options;
    private Integer indice_option;

    public Question(Integer idquestion, String text, String options, Integer indice_option) {
        this.idquestion = idquestion;
        this.text = text;
        this.options = options;
        this.indice_option = indice_option;
    }

    public Question(String text, String options, Integer indice_option) {
        this.text = text;
        this.options = options;
        this.indice_option = indice_option;
    }

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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getIndice_option() {
        return indice_option;
    }

    public void setIndice_option(Integer indice_option) {
        this.indice_option = indice_option;
    }

    @Override
    public String toString() {
        return "Question{" +
                "idquestion=" + idquestion +
                ", text='" + text + '\'' +
                ", options='" + options + '\'' +
                ", indice_option=" + indice_option +
                '}';
    }
}