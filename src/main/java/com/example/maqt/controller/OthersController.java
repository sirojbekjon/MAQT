package com.example.maqt.controller;

import com.example.maqt.entity.Others;
import com.example.maqt.service.OthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/others")
public class OthersController {
    @Autowired
    OthersService othersService;

    @PostMapping
    public HttpEntity<?> addOthers(@RequestBody Others others){
        return othersService.addOthers(others);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOthersById(@PathVariable Integer id){
        return othersService.getOtherById(id);
    }

    @GetMapping
    public HttpEntity<?> getOthers(){
        return othersService.getOthers();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editOthers(@PathVariable Integer id,@RequestBody Others others){
        return othersService.editOthers(id,others);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOthersById(@PathVariable Integer id){
        return othersService.deleteOthersById(id);
    }





}
