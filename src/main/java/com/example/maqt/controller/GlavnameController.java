package com.example.maqt.controller;

import com.example.maqt.entity.Glavname;
import com.example.maqt.service.GlavnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/glavname")
public class GlavnameController {

    @Autowired
    GlavnameService glavnameService;

    @PostMapping
    public HttpEntity<?> addGlavname(@RequestBody Glavname glavname){
        return glavnameService.addGlavname(glavname);
    }

    @GetMapping("/id")
    public HttpEntity<?> getGlavnameById(@PathVariable Integer id){

        return glavnameService.getGlavnameById(id);
    }

    @GetMapping
    public HttpEntity<?> getGlavnames(){
        return glavnameService.getGlavname();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editGlavName(@PathVariable Integer id,@RequestBody Glavname glavname){
        return glavnameService.editGlavName(id,glavname);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGlavName(@PathVariable Integer id){
        return glavnameService.deleteGlavName(id);
    }




}
