package com.example.demo.Controller;

import com.example.demo.Entity.Niveau;
import com.example.demo.Service.NiveauService;
import com.example.demo.Service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/niveau")
public class NiveauController {

    private final NiveauService niveauService;

    @Autowired
    public NiveauController( NiveauService niveauService) {
        this.niveauService = niveauService;
    }
    @GetMapping
    public ResponseEntity<List<Niveau>> getAllNiveaux() {
        List<Niveau> niveau =niveauService.getAllNiveaux();
        return ResponseEntity.ok(niveau);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Niveau> getNiveauById(@PathVariable Integer id) {
        Optional<Niveau> categorieOptional =niveauService.getNiveauById(id);
        return categorieOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Niveau> createNiveau(@RequestBody Niveau niveau) {
        Niveau createdNiveau =niveauService.createNiveau(niveau);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNiveau);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Niveau> updateNiveau(@PathVariable Integer id, @RequestBody Niveau newNiveau) {
        Niveau updatedNiveau =niveauService.updateNiveau(id, newNiveau);
        if (updatedNiveau != null) {
            return ResponseEntity.ok(updatedNiveau);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNiveau(@PathVariable Integer id) {
       niveauService.deleteNiveau(id);
        return ResponseEntity.noContent().build();
    }
}



