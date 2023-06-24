package com.douk.PMS.controller;

import com.douk.PMS.dto.CandidateDTO;
import com.douk.PMS.entity.Candidate;
import com.douk.PMS.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/candidate")
//@PreAuthorize("hasAuthority('HR')")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping(path = "save")
    public String addCandidate(@RequestBody CandidateDTO candidateDTO){
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
    public String rejectCandidate(@RequestParam(name = "id") Long candidateId){
        return candidateService.rejectCandidate(candidateId);
    }
}
