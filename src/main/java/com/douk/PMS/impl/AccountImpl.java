package com.douk.PMS.impl;

import com.douk.PMS.dto.LoginDTO;
import com.douk.PMS.entity.AccountRole;
import com.douk.PMS.payload.response.ResponseMessage;
import com.douk.PMS.repo.AccountRepository;
import com.douk.PMS.service.AccountService;
import com.douk.PMS.dto.AccountDTO;
import com.douk.PMS.entity.Account;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountImpl implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseMessage addAccount(AccountDTO accountDTO){
        boolean exist = accountRepository.existsByEmail(accountDTO.getEmail());
        if(exist)
            return new ResponseMessage("email is already exist", false) ;

        Account account = new Account(
                accountDTO.getEmail(),
                this.passwordEncoder.encode(accountDTO.getPassword()),
                AccountRole.ADMIN
        );

        accountRepository.save(account);
        return new ResponseMessage("success", true);
    }

    @Override
    public ResponseMessage loginAccount(LoginDTO loginDTO) {
        String msg = "";
        Account account = accountRepository.findByEmail(loginDTO.getEmail());
        if (account != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = account.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Account> optionalAccount = accountRepository.findByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
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
    public String deleteAccount(Long id) {
        boolean exist = accountRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("account not exist");
        }

        accountRepository.deleteById(id);

        return "successfully deleted";

    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional
    @Override
    public String updateAccountRole(Long id, AccountRole accountRole) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "account with id" + id +"not exist"));

        account.setAccountRole(accountRole);

        return "success update id : " + id +" , role : " + accountRole;
    }


}
