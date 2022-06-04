package com.example.maqt.service;

import com.example.maqt.entity.Categore;
import com.example.maqt.repository.CategoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoreService {

    @Autowired
    CategoreRepository categoreRepository;


    public HttpEntity<?> addCategore(Categore categore) {
        boolean existsByName = categoreRepository.existsByName(categore.getName());
        if (existsByName){
            return ResponseEntity.status(409).body("already exists");
        }
        Categore newCategore = new Categore();
        newCategore.setName(categore.getName());
        categoreRepository.save(newCategore);
        return ResponseEntity.status(200).body("saqlandi");
    }

    public HttpEntity<?> getCategoreById(Integer id) {
        Optional<Categore> optionalCategore = categoreRepository.findById(id);
        if (optionalCategore.isPresent()){
            Categore oldCategore = optionalCategore.get();
            return ResponseEntity.status(200).body(oldCategore);
        }

        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> getCategore() {
        List<Categore> categores = categoreRepository.findAll();
        if (!categores.isEmpty())
            return ResponseEntity.status(200).body(categores);
        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> editCategore(Integer id, Categore categore) {
        Optional<Categore> optionalCategore = categoreRepository.findById(id);
        if (optionalCategore.isPresent()){
            Categore oldCategore = optionalCategore.get();
            oldCategore.setName(categore.getName());
            categoreRepository.save(oldCategore);
            return ResponseEntity.status(200).body("o'zgartirildi");
        }
        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> deleteCategore(Integer id) {
        categoreRepository.deleteById(id);
        Optional<Categore> byId = categoreRepository.findById(id);
        if (!byId.isPresent())
            return ResponseEntity.status(200).body("O'chirildi");
        return ResponseEntity.status(409).body("O'chirilmadi");
    }
}
