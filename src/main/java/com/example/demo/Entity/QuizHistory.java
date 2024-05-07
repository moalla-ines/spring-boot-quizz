package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class QuizHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquizhistory;
    private Integer result ;

    @ManyToOne
    @JoinColumn(name = "id_user")

    @JsonIgnoreProperties
    private UserEntity user;

    @ManyToOne

    @JoinColumn(name = "id_quiz")

    @JsonIgnoreProperties("quiz")
    private Quiz quiz;



    public QuizHistory() {
    }

    public QuizHistory(Integer result, UserEntity user, Quiz quiz) {
        this.result = result;

        this.user = user;
        this.quiz = quiz;

    }

    public Integer getIdquizhistory() {
        return idquizhistory;
    }

    public void setIdquizhistory(Integer idquizhistory) {
        this.idquizhistory = idquizhistory;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }



    @Override
    public String toString() {
        return "QuizHistory{" +
                "idquizhistory=" + idquizhistory +
                ", user=" + user +
                ", quiz=" + quiz +
                '}';
    }


    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
