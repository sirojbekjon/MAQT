package com.example.maqt.controller;

import com.example.maqt.entity.Categore;
import com.example.maqt.service.CategoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categore")
public class CategoreController {
    @Autowired
    CategoreService categoreService;

    @PostMapping
    public HttpEntity<?> addCategore(@RequestBody Categore categore){
        return categoreService.addCategore(categore);
    }

    @GetMapping("/id")
    public HttpEntity<?> getCategoreById(@PathVariable Integer id){
        return categoreService.getCategoreById(id);
    }

    @GetMapping
    public HttpEntity<?> getCategores(){
        return categoreService.getCategore();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCategore(@PathVariable Integer id,@RequestBody Categore categore){
        return categoreService.editCategore(id,categore);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategore(@PathVariable Integer id){
        return categoreService.deleteCategore(id);
    }


}
