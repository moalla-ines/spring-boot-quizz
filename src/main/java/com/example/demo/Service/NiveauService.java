package com.example.demo.Service;

import com.example.demo.Entity.Niveau;

import java.util.List;
import java.util.Optional;

public interface NiveauService {
    List<Niveau> getAllNiveaux();

    Optional<Niveau> getNiveauById(Integer id);

    Niveau createNiveau(Niveau niveau);

    Niveau updateNiveau(Integer id, Niveau newNiveau);

    void deleteNiveau(Integer id);
}
