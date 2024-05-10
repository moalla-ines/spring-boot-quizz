package com.example.demo.Repository;

import com.example.demo.Entity.Niveau;
import com.example.demo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Query(value = "SELECT * role_id FROM type_user WHERE user_id = :iduser", nativeQuery = true)
    List<Role> findAllByUserId(@Param("iduser") Integer iduser);
}
