package com.example.maqt.service;

import com.example.maqt.entity.Glavname;
import com.example.maqt.repository.GlavnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlavnameService {

    @Autowired
    GlavnameRepository glavnameRepository;
    public HttpEntity<?> addGlavname(Glavname glavname) {
        boolean existsByName = glavnameRepository.existsByName(glavname.getName());
        if (existsByName){
            return ResponseEntity.status(409).body("already exists");
        }
        Glavname glavname1 = new Glavname();
        glavname1.setName(glavname.getName());
        glavnameRepository.save(glavname1);
        return ResponseEntity.status(200).body("saqlandi");
    }

    public HttpEntity<?> getGlavnameById(Integer id) {
        Optional<Glavname> optionalGlavname = glavnameRepository.findById(id);
        if (optionalGlavname.isPresent()){
            Glavname oldGlavname = optionalGlavname.get();
            return ResponseEntity.status(200).body(oldGlavname);
        }

        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> getGlavname() {
        List<Glavname> glavnames = glavnameRepository.findAll();
        if (!glavnames.isEmpty())
        return ResponseEntity.status(200).body(glavnames);
        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> editGlavName(Integer id, Glavname glavname) {
        Optional<Glavname> optionalGlavname = glavnameRepository.findById(id);
        if (optionalGlavname.isPresent()){
            Glavname oldGlavname = optionalGlavname.get();
            oldGlavname.setName(glavname.getName());
            glavnameRepository.save(oldGlavname);
            return ResponseEntity.status(200).body("o'zgartirildi");
        }
        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> deleteGlavName(Integer id) {
        glavnameRepository.deleteById(id);
        Optional<Glavname> byId = glavnameRepository.findById(id);
        if (!byId.isPresent())
            return ResponseEntity.status(200).body("O'chirildi");
        return ResponseEntity.status(409).body("O'chirilmadi");
    }
}
