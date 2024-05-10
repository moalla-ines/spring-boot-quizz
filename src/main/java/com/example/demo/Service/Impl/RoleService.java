package com.example.demo.Service.Impl;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    // Autres méthodes de service pour les opérations CRUD sur les rôles

}
