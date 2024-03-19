package com.example.demo.Controller;

import com.example.demo.Entity.Categorie;
import com.example.demo.Repository.CategorieRepository;
import com.example.demo.Service.CategorieService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {
    private final CategorieService categorieService;


    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategoryById(@PathVariable Integer id) {
        Optional<Categorie> categorieOptional = categorieService.findById(id);
        return categorieOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categorie> createCategory(@RequestBody Categorie categorie) {
        Categorie createdCategorie = categorieService.save(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategorie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategory(@PathVariable Integer id, @RequestBody Categorie newCategorie) {
        Optional<Categorie> optionalCategorie = categorieService.findById(id);
        if (optionalCategorie.isPresent()) {
            Categorie existingCategorie = optionalCategorie.get();
            existingCategorie.setTitre_categorie(newCategorie.getTitre_categorie());
            Categorie updatedCategorie = categorieService.save(existingCategorie);
            return ResponseEntity.ok(updatedCategorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categorieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
