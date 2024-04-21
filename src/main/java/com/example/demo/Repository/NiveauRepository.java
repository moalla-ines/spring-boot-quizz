package com.example.demo.Repository;

import com.example.demo.Entity.Niveau;
import com.example.demo.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NiveauRepository extends JpaRepository<Niveau, Integer> {

    @Query(value = "SELECT * FROM niveau WHERE id_categorie = :idCategorie", nativeQuery = true)
    List<Niveau> findAllByCategorieIdCategorie(@Param("idCategorie") Integer idCategorie);

}
