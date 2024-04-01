package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idscore;

    private Integer value;

    public Score() {
    }

    public Score(Integer idscore, Integer value) {
        this.idscore = idscore;
        this.value = value;
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
}
