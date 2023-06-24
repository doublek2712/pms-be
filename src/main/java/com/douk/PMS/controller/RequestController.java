package com.douk.PMS.controller;

import com.douk.PMS.dto.RequestDTO;
import com.douk.PMS.entity.Request;
import com.douk.PMS.payload.response.RequestPayrollResponse;
import com.douk.PMS.repo.RequestRepository;
import com.douk.PMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping(path = "save")
    public void addRequest(@RequestBody RequestDTO requestDTO){
        requestService.addRequest(requestDTO);
    }


    @PostMapping(path = "send")
    public RequestPayrollResponse sendRequest(@RequestBody EmailRequest emailRequest){
        return requestService.sendRequest(emailRequest.email);
    }

    record EmailRequest(String email) {}
    @PostMapping("verify")
    public Boolean verifyEmail(@RequestBody EmailRequest email){
        return requestService.verifyEmail(email.email);
    }

    @PreAuthorize("hasAuthority('ACCOUNTANT')")
    @DeleteMapping(path = "delete")
    public void deleteRequest(@RequestParam(name = "id") Long id){
        requestService.deleteRequest(id);
    }

    @PreAuthorize("hasAuthority('ACCOUNTANT')")
    @GetMapping(path = "all")
    public List<Request> getAllRequest(){
        return requestService.getAllRequest();
    }


}
