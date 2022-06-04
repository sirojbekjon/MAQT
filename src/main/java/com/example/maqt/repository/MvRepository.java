package com.example.maqt.repository;

import com.example.maqt.entity.Mv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Calendar;

public interface MvRepository extends JpaRepository<Mv,Integer> {
    boolean existsByNameAndDate(String name, String date);
}
