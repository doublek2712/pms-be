package com.douk.PMS.controller;

import com.douk.PMS.dto.RequestDTO;
import com.douk.PMS.entity.Request;
import com.douk.PMS.repo.RequestRepository;
import com.douk.PMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(path = "delete")
    public void deleteRequest(@RequestParam(name = "id") Long id){
        requestService.deleteRequest(id);
    }

    @GetMapping(path = "all")
    public List<Request> getAllRequest(){
        return requestService.getAllRequest();
    }


}
