package com.example.maqt.controller;

import com.example.maqt.entity.Mv;
import com.example.maqt.payload.MvDto;
import com.example.maqt.service.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mv")
public class MvController {
    @Autowired
    MvService mvService;

    @PostMapping
    public HttpEntity<?> addMv(@RequestBody MvDto mvDto){
       return mvService.addMv(mvDto);
    }

    @GetMapping
    public HttpEntity<?>getAllMv(){
        return mvService.getAllMv();
    }

    @GetMapping("/{id}")
    public HttpEntity<?>getMvById(@PathVariable Integer id){
        return mvService.getMvById(id);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editMv(@PathVariable Integer id,@RequestBody MvDto mvDto){
        return mvService.editMv(id,mvDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMv(@PathVariable Integer id){
        return mvService.deleteMv(id);
    }


}
