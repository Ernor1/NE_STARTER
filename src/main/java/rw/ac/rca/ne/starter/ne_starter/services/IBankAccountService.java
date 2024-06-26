package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateBankAccountDTO;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;

import java.util.List;
import java.util.UUID;

public interface IBankAccountService {
    BankAccount createBankAccount(CreateBankAccountDTO createBankAccountDTO);
    BankAccount findById(UUID id);
    List<BankAccount> findAll();
    List<BankAccount> findAllByCustomerId(UUID customerId);
}
