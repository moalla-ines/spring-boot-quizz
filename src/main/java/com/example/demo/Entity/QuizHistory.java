package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class QuizHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idquizhistory;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private Quiz quiz;
    @ManyToOne

    @JoinColumn(name = "id_score")
    private Score score;
    public QuizHistory() {
    }

    public QuizHistory(UserEntity user, Quiz quiz, Score score) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuizHistory{" +
                "idquizhistory=" + idquizhistory +
                ", user=" + user +
                ", quiz=" + quiz +
                ", score=" + score +
                '}';
    }
}
