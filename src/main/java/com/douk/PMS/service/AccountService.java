package com.douk.PMS.service;

import com.douk.PMS.dto.AccountDTO;
import com.douk.PMS.dto.LoginDTO;
import com.douk.PMS.entity.Account;
import com.douk.PMS.entity.AccountRole;

import com.douk.PMS.payload.response.ResponseMessage;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

public interface AccountService {



    ResponseMessage addAccount(AccountDTO accountDTO);

    ResponseMessage loginAccount(LoginDTO loginDTO);

    String deleteAccount(Long id);

    List<Account> getAllAccount();

    Optional<Account> getAccount(Long id);

    String updateAccountRole(Long id, AccountRole accountRole);
}
