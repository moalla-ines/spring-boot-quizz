package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
public class QuizHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_quiz", referencedColumnName = "idquiz")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "id_score", referencedColumnName = "idscore")
    private Score score;

    public QuizHistory() {
    }

    public QuizHistory(Integer id, UserEntity user, Quiz quiz, Score score) {
        this.id = id;
        this.user = user;
        this.quiz = quiz;
        this.score = score;
    }

    public QuizHistory(UserEntity user, Quiz quiz, Score score) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setQuiz(Quiz quizz) {
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
                "id=" + id +
                ", user=" + user +
                ", quiz=" + quizz +
                ", score=" + score +
                '}';
    }
}
