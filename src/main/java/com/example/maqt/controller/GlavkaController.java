package com.example.maqt.controller;

import com.example.maqt.payload.GlDto;
import com.example.maqt.payload.MvDto;
import com.example.maqt.service.GlavkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/glavka")
public class GlavkaController {
    
    @Autowired
    GlavkaService glavkaService;


    @PostMapping
    public HttpEntity<?> addGL(@RequestBody GlDto glDto){
        return glavkaService.addGl(glDto);
    }

    @GetMapping
    public HttpEntity<?>getAllGl(){
        return glavkaService.getAllGl();
    }

    @GetMapping("/{id}")
    public HttpEntity<?>getGlById(@PathVariable Integer id){
        return glavkaService.getGlById(id);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editMv(@PathVariable Integer id,@RequestBody GlDto glDto){
        return glavkaService.editGl(id,glDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGl(@PathVariable Integer id){
        return glavkaService.deleteGl(id);
    }


}
