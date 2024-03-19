package com.example.demo.Service;

import com.example.demo.Entity.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService {

    List<Categorie> findAll();

    Optional<Categorie> findById(Integer id);

    Categorie save(Categorie categorie);

    void deleteById(Integer id);

    List<Categorie> getAllCategories();

    Optional<Categorie> getCategoryById(Integer id);

    Categorie createCategory(Categorie categorie);

    Categorie updateCategory(Integer id, Categorie newCategorie);

    void deleteCategory(Integer id);

}