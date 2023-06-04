package com.douk.PMS.controller;

import com.douk.PMS.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/file")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = fileStorageService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }




    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] fileData=fileStorageService.downloadFile(fileName);

        //MediaType mediaType = fileData.

//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
//        mediaTypes.add(MediaType.valueOf("application/msword"));
//        mediaTypes.add(MediaType.valueOf("application/pdf"));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(fileData);

    }
}