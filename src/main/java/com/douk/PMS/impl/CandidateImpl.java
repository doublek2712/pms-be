package com.douk.PMS.impl;

import com.douk.PMS.dto.CandidateDTO;
import com.douk.PMS.dto.EmployeeDTO;
import com.douk.PMS.entity.Candidate;
import com.douk.PMS.repo.CandidateRepository;
import com.douk.PMS.service.CandidateService;
import com.douk.PMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private EmployeeService employeeService;
    @Override
    public String addCandidate(CandidateDTO candidateDTO) {
        Candidate newCandidate = new Candidate(
                candidateDTO.getFirstName(),
                candidateDTO.getLastName(),
                candidateDTO.getApplyPosition()
        );

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

        EmployeeDTO newEmployee = new EmployeeDTO(
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getApplyPosition()
        );
        employeeService.addEmployeeFromCandidate(newEmployee);
        candidateRepository.delete(candidate);
        return null;
    }

    @Override
    public String rejectCandidate(Long candidateId) {

        boolean exist = candidateRepository.existsById(candidateId);
        if(!exist)
            throw new IllegalStateException("candidate not exist");
        candidateRepository.deleteById(candidateId);
        return "success";
    }
}
