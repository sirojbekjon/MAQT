package com.example.maqt.service;

import com.example.maqt.entity.Categore;
import com.example.maqt.entity.Others;
import com.example.maqt.repository.CategoreRepository;
import com.example.maqt.repository.OthersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OthersService {

    @Autowired
    OthersRepository othersRepository;
    @Autowired
    CategoreRepository categoreRepository;
    public HttpEntity<?> addOthers(Others others) {
        boolean exists = othersRepository.existsByNameAndCategore_Id(others.getName(), others.getId());
        if (exists){
            return ResponseEntity.status(409).body("Bunday  fayl allaqachon yaratilgan");
        }
        Others newOthers = new Others();
        newOthers.setName(others.getName());
        Optional<Categore> optionalCategore = categoreRepository.findById(others.getId());
        if (optionalCategore.isPresent()) {
            Categore categore = optionalCategore.get();
            newOthers.setCategore(categore);
        }
        Others save = othersRepository.save(newOthers);
        Optional<Others> byId = othersRepository.findById(save.getId());
        if (byId.isPresent())
        return ResponseEntity.status(200).body("Saqlandi");
        return ResponseEntity.status(409).body("Saqlanmadi");
    }

    public HttpEntity<?> getOtherById(Integer id) {
        Optional<Others> optionalOthers = othersRepository.findById(id);
        if (optionalOthers.isPresent())
        return ResponseEntity.status(200).body(optionalOthers.get());
        return ResponseEntity.status(409).body("Malumotlar topilmadi");
    }

    public HttpEntity<?> getOthers(){
        List<Others> othersList = othersRepository.findAll();
        if (othersList.isEmpty())
        return ResponseEntity.status(200).body(othersList);
        return ResponseEntity.status(409).body("Malumotlar topilmadi");
    }

    public HttpEntity<?> editOthers(Integer id,Others others) {
        Optional<Others> optionalOthers = othersRepository.findById(id);
        if (optionalOthers.isPresent()){
            Others oldOthers = optionalOthers.get();
            oldOthers.setName(others.getName());
            Optional<Categore> categore = categoreRepository.findById(others.getId());
            oldOthers.setCategore(categore.get());
            return ResponseEntity.status(200).body("O'zgartirildi");
        }return ResponseEntity.status(409).body("Malumotlar topilmadi");
    }

    public HttpEntity<?> deleteOthersById(Integer id) {
        othersRepository.deleteById(id);
        Optional<Others> optionalOthers = othersRepository.findById(id);
        if (!optionalOthers.isPresent())
        return ResponseEntity.status(200).body("O'chirildi");
        return ResponseEntity.status(409).body("O'chirilmadi");
    }
}
