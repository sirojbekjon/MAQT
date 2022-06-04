package com.example.maqt.repository;

import com.example.maqt.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type,Integer> {
     List<Type> findAllByDegree(Integer degree);
}
