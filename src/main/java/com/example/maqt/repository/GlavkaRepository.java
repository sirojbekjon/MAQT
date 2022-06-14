package com.example.maqt.repository;

import com.example.maqt.entity.Glavka;
import com.example.maqt.entity.Mv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlavkaRepository extends JpaRepository<Glavka,Integer> {
    boolean existsByNumberAndDate(String number, String date);
}
