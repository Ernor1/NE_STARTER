package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateBankAccountDTO;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;

public interface IBankAccountService {
    BankAccount createBankAccount(CreateBankAccountDTO createBankAccountDTO);
}
