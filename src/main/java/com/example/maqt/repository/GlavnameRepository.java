package com.example.maqt.repository;

import com.example.maqt.entity.Glavname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlavnameRepository extends JpaRepository<Glavname,Integer> {

    boolean existsByName(String name);
}
