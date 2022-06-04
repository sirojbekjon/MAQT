package com.example.maqt.repository;

import com.example.maqt.entity.Categore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoreRepository extends JpaRepository<Categore,Integer> {
    boolean existsByName(String name);
}
