package com.douk.PMS.service;

import com.douk.PMS.dto.CandidateDTO;
import com.douk.PMS.entity.Candidate;

import java.util.List;

public interface CandidateService {

    String addCandidate(CandidateDTO candidateDTO);
    List<Candidate> getAllCandidate();
    String acceptCandidate(Long candidateId);
    String rejectCandidate(Long candidateId);
}
