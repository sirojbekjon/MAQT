package com.example.maqt.repository;

import com.example.maqt.entity.Others;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OthersRepository extends JpaRepository<Others,Integer> {
    boolean existsByNameAndCategore_Id(String name, Integer categore_id);
}
