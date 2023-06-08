package com.douk.PMS.controller;

import com.douk.PMS.dto.LoginDTO;
import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.entity.User;
import com.douk.PMS.payload.response.AuthenticationResponse;
import com.douk.PMS.payload.response.ResponseMessage;
import com.douk.PMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LoginDTO request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }




}
