package com.example.maqt.service;

import com.example.maqt.entity.State;
import com.example.maqt.entity.Type;
import com.example.maqt.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;
    public HttpEntity<?> addState(State state) {
        State newState = new State();
        newState .setName(state.getName());
        stateRepository.save(newState);
        Optional<State> optionalState = stateRepository.findById(newState.getId());
        if (optionalState.isPresent())
            return ResponseEntity.status(200).body(newState);
        return ResponseEntity.status(409).body("saqlanmadi");
    }

    public HttpEntity<?> getState(Integer id) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isPresent()){
            State state = optionalState.get();
            return ResponseEntity.status(200).body(state);
        }return ResponseEntity.status(409).body("State topilmadi");
    }

    public HttpEntity<?> getAllStates() {
        List<State> allStates = stateRepository.findAll();
        if (!allStates.isEmpty())
            return ResponseEntity.status(200).body(allStates);
        return ResponseEntity.status(409).body("Topilmadi");
    }

    public HttpEntity<?> editStates(Integer id,State state) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isPresent())
        {
            State oldState = optionalState.get();
            oldState.setName(state.getName());
            stateRepository.save(oldState);
            return ResponseEntity.status(200).body("O'zgartirildi");
        }
        return ResponseEntity.status(409).body("Not found");
    }

    public HttpEntity<?> deleteStates(Integer id) {
        stateRepository.deleteById(id);
        if (!stateRepository.findById(id).isPresent())
            return ResponseEntity.status(200).body("deleted");
        return ResponseEntity.status(409).body("Not deleted");
    }


}
