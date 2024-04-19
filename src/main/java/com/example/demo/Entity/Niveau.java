package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNiveau;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_categorie", referencedColumnName = "idcategorie")
    private Categorie categorie;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quiz> quizzes;
    public Niveau(Integer idNiveau, String name, Categorie categorie) {
        this.idNiveau = idNiveau;
        this.name = name;
        this.categorie = categorie;
    }

    public Niveau(Integer idNiveau, String name, Categorie categorie, List<Quiz> quizzes) {
        this.idNiveau = idNiveau;
        this.name = name;
        this.categorie = categorie;
        this.quizzes = quizzes;
    }

    public Niveau(String name, Categorie categorie) {
        this.name = name;
        this.categorie = categorie;
    }

    public Niveau() {
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Niveau{" +
                "idNiveau=" + idNiveau +
                ", name='" + name + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
