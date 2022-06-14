package com.example.maqt.service;

import com.example.maqt.entity.Attachment;
import com.example.maqt.entity.Ngsh;
import com.example.maqt.entity.State;
import com.example.maqt.entity.Type;
import com.example.maqt.payload.NgshDto;
import com.example.maqt.repository.AttachmentRepository;
import com.example.maqt.repository.NgshRepository;
import com.example.maqt.repository.StateRepository;
import com.example.maqt.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NgshService {

    @Autowired
    NgshRepository ngshRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    public HttpEntity<?> addNgsh(NgshDto ngshDto) {
        boolean exists = ngshRepository.existsByNameAndDate(ngshDto.getName(), ngshDto.getDate());
        if (exists)
        {
            return ResponseEntity.status(409).body("Fayil allaqachon qo'shilgan");
        }
        Ngsh newNgsh = new Ngsh();
        newNgsh.setName(ngshDto.getName());
        newNgsh.setNumber(ngshDto.getNumber());
        newNgsh.setDate(ngshDto.getDate());

        Optional<Type> optionalType = typeRepository.findById(ngshDto.getTypeId());
        Type type = optionalType.get();
        newNgsh.setType(type);

        if (ngshDto.getStateId()!=null) {
            Optional<State> optionalState = stateRepository.findById(ngshDto.getStateId());
            State state = optionalState.get();
            newNgsh.setState(state);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(ngshDto.getAttachment_id());
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            newNgsh.setAttachment(attachment);
        }

        Integer[] NgshArray = ngshDto.getNgshId();
        if (NgshArray!=null) {
            List<Ngsh> NgshList = new ArrayList<>();
            for (int i = 0; i < NgshArray.length; i++) {
                Optional<Ngsh> ngshOptional = ngshRepository.findById(NgshArray[i]);
                if (ngshOptional.isPresent()) {
                    Ngsh ngsh = ngshOptional.get();
                    NgshList.add(ngsh);
                }
            }
         newNgsh.setNgshList(NgshList);
        }

        ngshRepository.save(newNgsh);
return ResponseEntity.status(200).body("Ngsh saqlandi");
    }

    public HttpEntity<?> getAllNgsh() {
        List<Ngsh> ngshs = ngshRepository.findAll();
        if (!ngshs.isEmpty()){
        return ResponseEntity.status(200).body(ngshs);
        }
        return ResponseEntity.status(409).body("Malumot topilmadi");
    }


    public HttpEntity<?> getNgshById(Integer id) {
        Optional<Ngsh> optionalNgsh = ngshRepository.findById(id);
        if (optionalNgsh.isPresent()){
            Ngsh ngsh = optionalNgsh.get();
            return ResponseEntity.status(200).body(ngsh);
        }
        return ResponseEntity.status(409).body("Malumot topillmadi");
    }

    public HttpEntity<?> editNgsh(Integer id, NgshDto ngshDto) {
        Optional<Ngsh> optionalNgsh = ngshRepository.findById(id);
        if (optionalNgsh.isPresent()){
            Ngsh ngsh = optionalNgsh.get();
            ngsh.setName(ngshDto.getName());
            ngsh.setNumber(ngshDto.getNumber());
            ngsh.setDate(ngshDto.getDate());
            Optional<Type> optionalType = typeRepository.findById(ngshDto.getTypeId());
            ngsh.setType(optionalType.get());
            if (ngshDto.getStateId() != null){
                Optional<State> optionalState = stateRepository.findById(ngshDto.getStateId());
                ngsh.setState(optionalState.get());
            }

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(ngshDto.getAttachment_id());
            if (optionalAttachment.isPresent()){
                Attachment attachment = optionalAttachment.get();
                ngsh.setAttachment(attachment);
            }

            Integer[] NgshArray = ngshDto.getNgshId();
            if (NgshArray!=null) {
                List<Ngsh> NgshList = new ArrayList<>();
                for (int i = 0; i < NgshArray.length; i++) {
                    Optional<Ngsh> ngshOptional = ngshRepository.findById(NgshArray[i]);
                    if (ngshOptional.isPresent()) {
                        Ngsh oldNgsh = ngshOptional.get();
                        NgshList.add(oldNgsh);
                    }
                }
                ngsh.setNgshList(NgshList);
            }
            ngshRepository.save(ngsh);
        }
            return ResponseEntity.status(200).body("o'zgartirildi");
    }

    public HttpEntity<?> deleteNgsh(Integer id) {
        ngshRepository.deleteById(id);
        if (ngshRepository.findById(id).isPresent()){
            return ResponseEntity.status(409).body("O'chirilmadi");
        }return ResponseEntity.status(200).body("O'chirildi");
    }
}




