package com.example.maqt.repository;

import com.example.maqt.entity.Mv;
import com.example.maqt.entity.Ngsh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NgshRepository extends JpaRepository<Ngsh,Integer> {
    boolean existsByNameAndDate(String name, String date);
}
