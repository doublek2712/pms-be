package com.douk.PMS.impl;

import com.douk.PMS.config.JwtService;
import com.douk.PMS.dto.LoginDTO;
import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.entity.Role;
import com.douk.PMS.entity.User;
import com.douk.PMS.payload.response.AuthenticationResponse;
import com.douk.PMS.payload.response.ResponseMessage;

import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.repo.UserRepository;

import com.douk.PMS.service.EmailService;
import com.douk.PMS.service.UserService;
import com.douk.PMS.utils.PasswordGenerator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Override
    public AuthenticationResponse addUser(UserDTO userDTO){
        boolean exist = userRepository.existsByEmail(userDTO.getEmail());
        if(exist)
            throw new IllegalStateException("email is already exists");

        String randomPassword = PasswordGenerator.generatePassword(10);
        var user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(randomPassword))
                .userRole(Role.USER)
                .build();
        userRepository.save(user);
        emailService.sendPassword(userDTO, randomPassword);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());


        if(user.get().getUserRole().toString() == "BOD"){
            return AuthenticationResponse.builder()
                    .status("this is BOD")
                    .build();
        }

        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .userRole(user.get().getUserRole().toString())
                .status("success")
                .build();
    }

    @Override
    public ResponseMessage login(LoginDTO loginDTO) {
        String msg = "";
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.get().getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> optionalAccount = userRepository.findByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (optionalAccount.isPresent()) {
                    return new ResponseMessage("Login Success", true);
                } else {
                    return new ResponseMessage("Login Failed", false);
                }
            } else {

                return new ResponseMessage("password Not Match", false);
            }
        }else {
            return new ResponseMessage("Email not exits", false);
        }
    }

    @Override
    public String deleteUser(Long id) {
        boolean exist = userRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("user not exist");
        }

        userRepository.deleteById(id);

        return "successfully deleted";

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUserByRole(Role role) {
        return userRepository.findAllByUserRole(role);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public String updateUserRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id" + id +"not exist"));

        user.setUserRole(role);
        return "success update id : " + id +" , role : " + role;
    }

    @Transactional
    @Override
    public String updatePassword(Long id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id" + id +"not exist"));

        user.setPassword(passwordEncoder.encode(password));
        return "success update password for user: " + user.getPassword();

    }


}
