package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idscore;

    private Integer value;
    @ManyToOne
    @JoinColumn(name = "idquestion")
    @JsonBackReference
    private Question question;

    @OneToMany(mappedBy = "score", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private List<QuizHistory> quizHistoryList;

    public Score() {
    }

    public Score(Integer idscore, Integer value, Question question, List<QuizHistory> quizHistoryList) {
        this.idscore = idscore;
        this.value = value;
        this.question = question;

        this.quizHistoryList = quizHistoryList;
    }

    public Integer getIdscore() {
        return idscore;
    }

    public void setIdscore(Integer idscore) {
        this.idscore = this.idscore;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Score{" +
                "idscore=" + idscore +
                ", value=" + value +
                '}';
    }

    public List<QuizHistory> getQuizHistoryList() {
        return quizHistoryList;
    }

    public void setQuizHistoryList(List<QuizHistory> quizHistoryList) {
        this.quizHistoryList = quizHistoryList;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
