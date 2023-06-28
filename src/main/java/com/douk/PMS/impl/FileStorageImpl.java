package com.douk.PMS.impl;

import com.douk.PMS.entity.FileStorage;
import com.douk.PMS.repo.FileStorageRepository;
import com.douk.PMS.service.FileStorageService;
import com.douk.PMS.utils.FileStorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileStorageImpl implements FileStorageService {

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        FileStorage imageData = fileStorageRepository.save(FileStorage.builder()
                .filename(file.getOriginalFilename())
                .type(file.getContentType())
                .data(FileStorageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return file.getOriginalFilename();
        }
        return "fail to upload";
    }

    @Override
    public byte[] downloadFile(String fileName) {
        Optional<FileStorage> dbFileData = fileStorageRepository.findByFilename(fileName);
        byte[] file=FileStorageUtils.decompressImage(dbFileData.get().getData());
        return file;
    }
}
