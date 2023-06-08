package com.douk.PMS.service;

import com.douk.PMS.dto.LoginDTO;


import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.entity.Role;
import com.douk.PMS.entity.User;
import com.douk.PMS.payload.response.AuthenticationResponse;
import com.douk.PMS.payload.response.ResponseMessage;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AuthenticationResponse addUser(UserDTO accountDTO);

    AuthenticationResponse authenticate(LoginDTO loginDTO);

    ResponseMessage login(LoginDTO loginDTO);

    String deleteUser(Long id);

    List<User> getAllUser();

    List<User> getAllUserByRole(Role role);

    Optional<User> getUser(Long id);

    String updateUserRole(Long id, Role role);

    String updatePassword(Long id, String password);
}
