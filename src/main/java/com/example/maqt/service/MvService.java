package com.example.maqt.service;

import com.example.maqt.entity.Attachment;
import com.example.maqt.entity.Mv;
import com.example.maqt.entity.State;
import com.example.maqt.entity.Type;
import com.example.maqt.payload.MvDto;
import com.example.maqt.repository.AttachmentRepository;
import com.example.maqt.repository.MvRepository;
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
public class MvService {

    @Autowired
    MvRepository mvRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    public HttpEntity<?> addMv(MvDto mvDto) {
        boolean exists = mvRepository.existsByNumberAndDate(mvDto.getNumber(), mvDto.getDate());
        if (exists)
        {
            return ResponseEntity.status(409).body("Fayil allaqachon qo'shilgan");
        }
        Mv newMv = new Mv();
        newMv.setName(mvDto.getName());
        newMv.setNumber(mvDto.getNumber());
        newMv.setDate(mvDto.getDate());

        Optional<Type> optionalType = typeRepository.findById(mvDto.getTypeId());
        Type type = optionalType.get();
        newMv.setType(type);

        if (mvDto.getStateId()!=null) {
            Optional<State> optionalState = stateRepository.findById(mvDto.getStateId());
            State state = optionalState.get();
            newMv.setState(state);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(mvDto.getAttachment_id());
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            newMv.setAttachment(attachment);
        }

        Integer[] MvArray = mvDto.getMvId();
        if (MvArray!=null) {
            List<Mv> MvList = new ArrayList<>();
            for (int i = 0; i < MvArray.length; i++) {
                Optional<Mv> mvOptional = mvRepository.findById(MvArray[i]);
                if (mvOptional.isPresent()) {
                    Mv mv = mvOptional.get();
                    MvList.add(mv);
                }
            }
         newMv.setMvList(MvList);
        }

        mvRepository.save(newMv);
    return ResponseEntity.status(200).body("Mv saqlandi");
    }

    public HttpEntity<?> getAllMv() {
        List<Mv> mvs = mvRepository.findAll();
        if (!mvs.isEmpty()){
        return ResponseEntity.status(200).body(mvs);
        }
        return ResponseEntity.status(409).body("Malumot topilmadi");
    }


    public HttpEntity<?> getMvById(Integer id) {
        Optional<Mv> optionalMv = mvRepository.findById(id);
        if (optionalMv.isPresent()){
            Mv mv = optionalMv.get();
            return ResponseEntity.status(200).body(mv);
        }
        return ResponseEntity.status(409).body("Malumot topillmadi");
    }

    public HttpEntity<?> editMv(Integer id, MvDto mvDto) {
        Optional<Mv> optionalMv = mvRepository.findById(id);
        if (optionalMv.isPresent()){
            Mv mv = optionalMv.get();
            mv.setName(mvDto.getName());
            mv.setNumber(mvDto.getNumber());
            mv.setDate(mvDto.getDate());
            Optional<Type> optionalType = typeRepository.findById(mvDto.getTypeId());
            mv.setType(optionalType.get());
            if (mvDto.getStateId() != null){
                Optional<State> optionalState = stateRepository.findById(mvDto.getStateId());
                mv.setState(optionalState.get());
            }
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(mvDto.getAttachment_id());
            if (optionalAttachment.isPresent()){
                Attachment attachment = optionalAttachment.get();
                mv.setAttachment(attachment);
            }

            Integer[] MvArray = mvDto.getMvId();
            if (MvArray!=null) {
                List<Mv> MvList = new ArrayList<>();
                for (int i = 0; i < MvArray.length; i++) {
                    Optional<Mv> mvOptional = mvRepository.findById(MvArray[i]);
                    if (mvOptional.isPresent()) {
                        Mv oldMv = mvOptional.get();
                        MvList.add(oldMv);
                    }
                }
                mv.setMvList(MvList);
            }
            mvRepository.save(mv);
        }
            return ResponseEntity.status(200).body("o'zgartirildi");
    }

    public HttpEntity<?> deleteMv(Integer id) {
        mvRepository.deleteById(id);
        if (mvRepository.findById(id).isPresent()){
            return ResponseEntity.status(409).body("O'chirilmadi");
        }return ResponseEntity.status(200).body("O'chirildi");
    }
}




