package com.douk.PMS.controller;

import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.entity.Role;
import com.douk.PMS.entity.User;
import com.douk.PMS.payload.response.AuthenticationResponse;
import com.douk.PMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(path = "save")
    public AuthenticationResponse register(@RequestBody UserDTO request){
        return userService.addUser(request);
    }



    @DeleteMapping(path = "delete")
    public String deleteUser(@RequestParam(name = "id") Long id){
        return userService.deleteUser(id);

    }

    @GetMapping(path = "all")
    public List<User> getAllUser(
            @RequestParam(name = "role", required = false) Role role
    ){
        List<User> users;
        if(role != null){
            users = userService.getAllUserByRole(role);
        }
        else
            users = userService.getAllUser();
        return users;
    }


    @GetMapping
    public Optional<User> getUser(@RequestParam(name = "id") Long id){
        return userService.getUser(id);
    }

//    @RequestParam(name = "role", required = false) AccountRole accountRole
    @PutMapping("updateRole/{id}/{role}")
    public String updateUserRole(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "role") Role accountRole){
       return  userService.updateUserRole(id, accountRole);
    }

    @PutMapping(path = "updatePassword")
    public String updateUserPass(
            @RequestParam(name = "id") Long id,
            @RequestBody String password
    ){
        return userService.updatePassword(id, password);
    }

}
