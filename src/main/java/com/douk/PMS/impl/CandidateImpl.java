package com.douk.PMS.impl;

import com.douk.PMS.dto.CandidateDTO;
import com.douk.PMS.dto.EmployeeDTO;
import com.douk.PMS.entity.Candidate;
import com.douk.PMS.entity.FileStorage;
import com.douk.PMS.repo.CandidateRepository;
import com.douk.PMS.repo.FileStorageRepository;
import com.douk.PMS.service.CandidateService;
import com.douk.PMS.service.EmployeeService;
import com.douk.PMS.service.FileStorageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @Autowired
    private EmployeeService employeeService;
    @Override
    public String addCandidate(CandidateDTO candidateDTO) {
//        String uploadRes = fileStorageService.uploadFile();
//        if(uploadRes.equals("fail to upload")){
//            throw new IllegalStateException("cv fail to upload");
//        }

        Optional<FileStorage> fileStorage = fileStorageRepository.findByFilename(candidateDTO.getCv());
        if(fileStorage.isEmpty())
            throw new IllegalStateException("can't find cv, something wrong");


        Candidate newCandidate = Candidate.builder()
                .firstName(candidateDTO.getFirstName())
                .lastName(candidateDTO.getLastName())
                .applyPosition(candidateDTO.getApplyPosition())
                .email(candidateDTO.getEmail())
                .gender(candidateDTO.getGender())
                .cv(fileStorage.get())
                .build();

        candidateRepository.save(newCandidate);
        return null;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    @Override
    public String acceptCandidate(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId).get();

        EmployeeDTO newEmployee = EmployeeDTO.builder()
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .position(candidate.getApplyPosition())
                .gender(candidate.getGender())
                .build();
        candidateRepository.delete(candidate);
        return employeeService.addEmployeeFromCandidate(newEmployee);

    }

    @Override
    public String rejectCandidate(Long candidateId) {



        boolean exist = candidateRepository.existsById(candidateId);
        if(!exist)
            throw new IllegalStateException("candidate not exist");
        candidateRepository.deleteById(candidateId);
        return "candidates was rejected";
    }

    @Transactional
    @Override
    public String uploadInterviewResult(Long id, MultipartFile file) throws IOException{
        //xu li candidate
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isEmpty())
            throw new IllegalStateException("candidate not found");


        // xu li file
        String uploadRes = fileStorageService.uploadFile(file);
        if(uploadRes.equals("fail to upload")){
            throw new IllegalStateException("cv fail to upload");
        }

        Optional<FileStorage> fileStorage = fileStorageRepository.findByFilename(uploadRes);
        if(fileStorage.isEmpty())
            throw new IllegalStateException("can't find file, something wrong");


        //do ne
        candidate.get().setInterview_result(fileStorage.get());


        return "upload file successful";
    }
}
