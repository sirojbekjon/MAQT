package com.example.maqt.controller;

import com.example.maqt.payload.NgshDto;
import com.example.maqt.service.NgshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ngsh")
public class NgshController {
    @Autowired
    NgshService ngshService;

    @PostMapping
    public HttpEntity<?> addNgsh(@RequestBody NgshDto ngshDto){
       return ngshService.addNgsh(ngshDto);
    }

    @GetMapping
    public HttpEntity<?>getAllNgsh(){
        return ngshService.getAllNgsh();
    }

    @GetMapping("/{id}")
    public HttpEntity<?>getNgshById(@PathVariable Integer id){
        return ngshService.getNgshById(id);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editNgsh(@PathVariable Integer id,@RequestBody NgshDto ngshDto){
        return ngshService.editNgsh(id,ngshDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteNgsh(@PathVariable Integer id){
        return ngshService.deleteNgsh(id);
    }


}
