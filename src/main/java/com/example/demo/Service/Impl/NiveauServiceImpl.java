package com.example.demo.Service.Impl;

import com.example.demo.Entity.Niveau;
import com.example.demo.Repository.NiveauRepository;
import com.example.demo.Service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NiveauServiceImpl implements NiveauService {
    private final NiveauRepository niveauRepository;

    @Autowired
    public NiveauServiceImpl(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    @Override
    public List<Niveau> getAllNiveaux() {
        return niveauRepository.findAll();
    }

    @Override
    public Optional<Niveau> getNiveauById(Integer id) {
        return niveauRepository.findById(id);
    }

    @Override
    public Niveau createNiveau(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    @Override
    public Niveau updateNiveau(Integer id, Niveau newNiveau) {
        Optional<Niveau> optionalNiveau = niveauRepository.findById(id);
        if (optionalNiveau.isPresent()) {
            Niveau existingNiveau = optionalNiveau.get();
            existingNiveau.setName(newNiveau.getName());
            return niveauRepository.save(existingNiveau);
        } else {
            throw new RuntimeException("Niveau not found with id: " + id);
        }
    }

    @Override
    public void deleteNiveau(Integer id) {
        niveauRepository.deleteById(id);
    }

    @Override
    public List<Niveau> getNiveauByCategorie(Integer idcategorie) {
        return niveauRepository.findByCategorieIdcategorie(idcategorie);
    }
}
