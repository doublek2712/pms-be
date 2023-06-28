package com.douk.PMS.controller;

import com.douk.PMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/request")
public class RequestController {

    @Autowired
    private RequestService requestService;
    @PostMapping(path = "send/{email}")
    public String sendRequest(@PathVariable("email") String emailRequest,
                                @RequestParam("file") MultipartFile file) throws IOException {
        return requestService.sendRequest(emailRequest, file);
    }

    record PayrollRequest(String email, MultipartFile file) {}
    record EmailRequest(String email) {}



    @PostMapping("verify")
    public Boolean verifyEmail(@RequestBody EmailRequest emailRequest){
        return requestService.verifyEmail(emailRequest.email);
    }


}
