package com.example.maqt.controller;

import com.example.maqt.entity.Type;
import com.example.maqt.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @PostMapping
    public HttpEntity<?> addType(@RequestBody Type type){
        return typeService.addType(type);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getType(@PathVariable Integer id){
        return typeService.getType(id);
    }

    @GetMapping
    public HttpEntity<?> getAllTypes(){
        return typeService.getAllTypes();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editTypes(@PathVariable Integer id,@RequestBody Type type){
        return typeService.editTypes(id,type);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTypes(@PathVariable Integer id){
        return typeService.deleteTypes(id);
    }
}
