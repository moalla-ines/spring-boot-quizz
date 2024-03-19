package com.example.demo.Entity;

public class Question {
    private Integer id_question;
    private String text;
    private String options ;
    private Integer indice_option;

    public Question(Integer id_question, String text, String options, Integer indice_option) {
        this.id_question = id_question;
        this.text = text;
        this.options = options;
        this.indice_option = indice_option;
    }

    public Question(String text, String options, Integer indice_option) {
        this.text = text;
        this.options = options;
        this.indice_option = indice_option;
    }

    public Question() {
    }

    public Integer getId_question() {
        return id_question;
    }

    public void setId_question(Integer id_question) {
        this.id_question = id_question;
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
                "id_question=" + id_question +
                ", text='" + text + '\'' +
                ", options='" + options + '\'' +
                ", indice_option=" + indice_option +
                '}';
    }
}
