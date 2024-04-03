package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idscore;

    private Integer value;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizHistory> quizHistoryList;

    public Score() {
    }

    public Score(Integer idscore, Integer value, List<QuizHistory> quizHistoryList) {
        this.idscore = idscore;
        this.value = value;

        this.quizHistoryList = quizHistoryList;
    }

    public Integer getIdscore() {
        return idscore;
    }

    public void setIdscore(Integer id) {
        this.idscore = idscore;
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
}
