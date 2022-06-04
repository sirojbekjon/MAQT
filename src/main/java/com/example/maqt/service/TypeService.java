package com.example.maqt.service;

import com.example.maqt.entity.Type;
import com.example.maqt.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;
    public HttpEntity<?> addType(Type type) {
        Type newType = new Type();
        newType .setName(type.getName());
        newType.setDegree(type.getDegree());
        typeRepository.save(newType);
        Optional<Type> typeOptional = typeRepository.findById(newType.getId());
        if (typeOptional.isPresent())
               return ResponseEntity.status(200).body(newType);
        return ResponseEntity.status(409).body("saqlanmadi");
    }

    public HttpEntity<?> getType(Integer id) {
        Optional<Type> optionalType = typeRepository.findById(id);
        if (optionalType.isPresent()){
            Type type = optionalType.get();
            return ResponseEntity.status(200).body(type);
        }return ResponseEntity.status(409).body("Type topilmadi");
    }

    public HttpEntity<?> getAllTypes() {
        List<Type> allTypes = typeRepository.findAll();
        if (!allTypes.isEmpty())
        return ResponseEntity.status(200).body(allTypes);
        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> editTypes(Integer id,Type type) {
        Optional<Type> optionalType = typeRepository.findById(id);
        if (optionalType.isPresent())
        {
            Type oldType = optionalType.get();
            oldType.setName(type.getName());
            oldType.setDegree(type.getDegree());
            typeRepository.save(oldType);
            return ResponseEntity.status(200).body("saqlandi");
        }
        return ResponseEntity.status(409).body("Not found");
    }

    public HttpEntity<?> deleteTypes(Integer id) {
        typeRepository.deleteById(id);
        if (!typeRepository.findById(id).isPresent())
               return ResponseEntity.status(200).body("deleted");
        return ResponseEntity.status(409).body("Not deleted");
    }
}
