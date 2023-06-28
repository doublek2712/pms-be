package com.douk.PMS.controller;

import com.douk.PMS.dto.CandidateDTO;
import com.douk.PMS.entity.Candidate;
import com.douk.PMS.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/candidate")
//@PreAuthorize("hasAuthority('HR')")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping(path = "save")
    public String addCandidate(
            @RequestBody CandidateDTO candidateDTO){
        return candidateService.addCandidate(candidateDTO);
    }

    @GetMapping(path = "all")
    public List<Candidate> getAllCandidate(){
        return candidateService.getAllCandidate();
    }

    @PostMapping(path = "accept")
    public String acceptCandidate(@RequestParam(name = "id") Long candidateId){
        return candidateService.acceptCandidate(candidateId);
    }

    @DeleteMapping(path = "reject")
    public String rejectCandidate(@RequestParam("id") Long candidateId){
        return candidateService.rejectCandidate(candidateId);
    }

    @PutMapping("update/{id}")
    public String uploadInterviewResult(
            @PathVariable("id") Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        return candidateService.uploadInterviewResult(id, file);
    }
}
