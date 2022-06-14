package com.example.maqt.service;

import com.example.maqt.entity.*;
import com.example.maqt.payload.GlDto;
import com.example.maqt.payload.MvDto;
import com.example.maqt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GlavkaService {

    @Autowired
    GlavkaRepository glavkaRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    GlavnameRepository glavnameRepository;
    public HttpEntity<?> addGl(GlDto glDto) {
        boolean exists = glavkaRepository.existsByNumberAndDate(glDto.getNumber(), glDto.getDate());
        if (exists)
        {
            return ResponseEntity.status(409).body("Fayil allaqachon qo'shilgan");
        }
        Glavka newGl = new Glavka();
        newGl.setName(glDto.getName());
        newGl.setNumber(glDto.getNumber());
        newGl.setDate(glDto.getDate());

        Optional<Type> optionalType = typeRepository.findById(glDto.getTypeId());
        Type type = optionalType.get();
        newGl.setType(type);

        if (glDto.getStateId()!=null) {
            Optional<State> optionalState = stateRepository.findById(glDto.getStateId());
            State state = optionalState.get();
            newGl.setState(state);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(glDto.getAttachment_id());
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            newGl.setAttachment(attachment);
        }

        //GLAVKA NAME NI SAQLAB OLAMIZ
        Optional<Glavname> optionalGlavName = glavnameRepository.findById(glDto.getGlavkaName_id());
        if (optionalGlavName.isPresent()){
            Glavname glavname = optionalGlavName.get();
            newGl.setGlavName(glavname);
        }


        Integer[] GlArray = glDto.getGlavka_id();
        if (GlArray!=null) {
            List<Glavka> GlList = new ArrayList<>();
            for (int i = 0; i < GlArray.length; i++) {
                Optional<Glavka> glOptional = glavkaRepository.findById(GlArray[i]);
                if (glOptional.isPresent()) {
                    Glavka glavka = glOptional.get();
                    GlList.add(glavka);
                }
            }
         newGl.setGlavkaList(GlList);
        }
        glavkaRepository.save(newGl);
    return ResponseEntity.status(200).body("Gl saqlandi");
    }


    public HttpEntity<?> getAllGl() {
        List<Glavka> glavkas = glavkaRepository.findAll();
        if (!glavkas.isEmpty()){
        return ResponseEntity.status(200).body(glavkas);
        }
        return ResponseEntity.status(409).body("Malumot topilmadi");
    }


    public HttpEntity<?> getGlById(Integer id) {
        Optional<Glavka> optionalGl = glavkaRepository.findById(id);
        if (optionalGl.isPresent()){
            Glavka glavka = optionalGl.get();
            return ResponseEntity.status(200).body(glavka);
        }
        return ResponseEntity.status(409).body("Malumot topillmadi");
    }


    public HttpEntity<?> editGl(Integer id, GlDto glDto) {
        Optional<Glavka> optionalGl = glavkaRepository.findById(id);
        if (optionalGl.isPresent()){
            Glavka glavka = optionalGl.get();
            glavka.setName(glDto.getName());
            glavka.setNumber(glDto.getNumber());
            glavka.setDate(glDto.getDate());
            Optional<Type> optionalType = typeRepository.findById(glDto.getTypeId());
            glavka.setType(optionalType.get());
            if (glDto.getStateId() != null){
                Optional<State> optionalState = stateRepository.findById(glDto.getStateId());
                glavka.setState(optionalState.get());
            }
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(glDto.getAttachment_id());
            if (optionalAttachment.isPresent()){
                Attachment attachment = optionalAttachment.get();
                glavka.setAttachment(attachment);
            }

            Optional<Glavname> optionalGlavName = glavnameRepository.findById(glDto.getGlavkaName_id());
            if (optionalGlavName.isPresent()){
                Glavname glavname = optionalGlavName.get();
                glavka.setGlavName(glavname);
            }


            Integer[] GlArray = glDto.getGlavka_id();
            if (GlArray!=null) {
                List<Glavka> GlList = new ArrayList<>();
                for (int i = 0; i < GlArray.length; i++) {
                    Optional<Glavka> glOptional = glavkaRepository.findById(GlArray[i]);
                    if (glOptional.isPresent()) {
                        Glavka oldglavka = glOptional.get();
                        GlList.add(oldglavka);
                    }
                }
                glavka.setGlavkaList(GlList);
            }
            glavkaRepository.save(glavka);
        }
            return ResponseEntity.status(200).body("o'zgartirildi");
    }


    public HttpEntity<?> deleteGl(Integer id) {
        glavkaRepository.deleteById(id);
        if (glavkaRepository.findById(id).isPresent()){
            return ResponseEntity.status(409).body("O'chirilmadi");
        }return ResponseEntity.status(200).body("O'chirildi");
    }
}




