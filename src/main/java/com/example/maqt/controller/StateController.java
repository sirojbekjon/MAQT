package com.example.maqt.controller;

import com.example.maqt.entity.State;
import com.example.maqt.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    StateService stateService;

    @PostMapping
    public HttpEntity<?> addState(@RequestBody State state){
        return stateService.addState(state);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getState(@PathVariable Integer id){
        return stateService.getState(id);
    }

    @GetMapping
    public HttpEntity<?> getAllStates(){
        return stateService.getAllStates();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editStates(@PathVariable Integer id,@RequestBody State state){
        return stateService.editStates(id,state);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStates(@PathVariable Integer id){
        return stateService.deleteStates(id);
    }
}
