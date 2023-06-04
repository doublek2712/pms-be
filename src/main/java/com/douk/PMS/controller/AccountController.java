package com.douk.PMS.controller;

import com.douk.PMS.dto.AccountDTO;
import com.douk.PMS.dto.LoginDTO;
import com.douk.PMS.entity.Account;
import com.douk.PMS.entity.AccountRole;

import com.douk.PMS.payload.response.ResponseMessage;
import com.douk.PMS.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "save")
    public ResponseMessage register(@RequestBody AccountDTO request){
        return accountService.addAccount(request);
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        ResponseMessage loginMessage = accountService.loginAccount(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

    @DeleteMapping(path = "delete")
    public String deleteAccount(@RequestParam(name = "id") Long id){
        return accountService.deleteAccount(id);

    }

    @GetMapping(path = "all")
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }

    @GetMapping
    public Optional<Account> getAccount(@RequestParam(name = "id") Long id){
        return accountService.getAccount(id);
    }

//    @RequestParam(name = "role", required = false) AccountRole accountRole
    @PutMapping(path = "update")
    public String updateAccountRole(@RequestParam(name = "id") Long id,
                                    @RequestBody AccountRole accountRole){
       return  accountService.updateAccountRole(id, accountRole);
    }

}
