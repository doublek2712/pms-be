package com.douk.PMS.service;

import com.douk.PMS.dto.CandidateDTO;
import com.douk.PMS.entity.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidateService {

    String addCandidate(CandidateDTO candidateDTO);
    List<Candidate> getAllCandidate();
    String acceptCandidate(Long candidateId);
    String rejectCandidate(Long candidateId);

    String uploadInterviewResult(Long id, MultipartFile file) throws IOException;

}
