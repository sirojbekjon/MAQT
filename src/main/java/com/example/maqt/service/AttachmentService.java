package com.example.maqt.service;

import com.example.maqt.entity.Attachment;
import com.example.maqt.repository.AttachmentRepository;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {


    private final AttachmentRepository attachmentRepository;
    private final static String uploadDirectory="Upload";
    @SneakyThrows
    public HttpEntity<?> addFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();

        MultipartFile file = request.getFile(fileNames.next());
        if (file != null){
            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());

            String originalFilename = file.getOriginalFilename();
            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID().toString()+"."+split[split.length-1];
            Path path = Paths.get(uploadDirectory+"/"+name);
            Files.copy(file.getInputStream(),path);

            InputStream docFile = new FileInputStream(new File(uploadDirectory+"/"+name));
            XWPFDocument doc = new XWPFDocument(docFile);
            PdfOptions pdfOptions = PdfOptions.create();
            if (name.endsWith(".docx")) {
            OutputStream out = new FileOutputStream(new File(uploadDirectory + "/" + name.substring(0, name.length() - 5) + ".pdf"));
            PdfConverter.getInstance().convert(doc,out,pdfOptions);
            attachment.setName(name.substring(0, name.length() - 5) + ".pdf");
                doc.close();
                out.close();
            }else if (name.endsWith(".doc")) {
                OutputStream out = new FileOutputStream(new File(uploadDirectory + "/" + name.substring(0, name.length() - 4) + ".pdf"));
                PdfConverter.getInstance().convert(doc,out,pdfOptions);
                doc.close();
                out.close();
            }
            System.out.println("done pdf");
            Files.delete(Paths.get(uploadDirectory+"/"+name));
            Attachment savedFile = attachmentRepository.save(attachment);
            return ResponseEntity.status(200).body(savedFile.getId());
        }
        return ResponseEntity.status(400).body("file tanlanmagan");
    }

    public HttpEntity<?> editFile(Integer id, MultipartHttpServletRequest request) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment oldFile = optionalAttachment.get();

            //ESKI FILENI SERVERDAN O'CHIRIB YUBORAMIZ
            String deleteFile = oldFile.getName();
            Files.delete(Paths.get(uploadDirectory+"/"+deleteFile));

                Iterator<String> fileNames = request.getFileNames();
                MultipartFile file = request.getFile(fileNames.next());
                if (file!=null){
                oldFile.setFileOriginalName(file.getOriginalFilename());
                oldFile.setSize(file.getSize());
                oldFile.setContentType(file.getContentType());

                String originalFilename = file.getOriginalFilename();
                String[] split = originalFilename.split("\\.");
                String name = UUID.randomUUID().toString()+"."+split[split.length-1];
                Path path = Paths.get(uploadDirectory+"/"+name);
                Files.copy(file.getInputStream(),path);

                InputStream docFile = new FileInputStream(new File(uploadDirectory+"/"+name));
                XWPFDocument doc = new XWPFDocument(docFile);
                PdfOptions pdfOptions = PdfOptions.create();
                if (name.endsWith(".docx")) {
                    OutputStream out = new FileOutputStream(new File(uploadDirectory + "/" + name.substring(0, name.length() - 5) + ".pdf"));
                    PdfConverter.getInstance().convert(doc,out,pdfOptions);
                    oldFile.setName(name.substring(0, name.length() - 5) + ".pdf");
                    doc.close();
                    out.close();
                }else if (name.endsWith(".doc")) {
                    OutputStream out = new FileOutputStream(new File(uploadDirectory + "/" + name.substring(0, name.length() - 4) + ".pdf"));
                    PdfConverter.getInstance().convert(doc,out,pdfOptions);
                    doc.close();
                    out.close();
                }
                System.out.println("done pdf");
                Files.delete(Paths.get(uploadDirectory+"/"+name));
            }
            Attachment savedFile = attachmentRepository.save(oldFile);
            return ResponseEntity.status(200).body(savedFile.getId());
        }
        return ResponseEntity.status(400).body("file mavjud emas");
    }
}
