package com.example.maqt.controller;

import com.example.maqt.repository.AttachmentRepository;
import com.example.maqt.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;


@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentService attachmentService;
    //FILENI SERVERGA YUKLASH
    @PostMapping("/uploadSystem")
    public HttpEntity<?> uploadFileToFileSystem(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.addFile(request);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editFileSystem(@PathVariable Integer id,MultipartHttpServletRequest request) throws IOException {
        return attachmentService.editFile(id,request);
    }
}
