package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAccountSaveDTO;
import rw.ac.rca.ne.starter.ne_starter.models.AccountSave;

import java.util.List;
import java.util.UUID;

public interface AccountSaveService {
    AccountSave save(CreateAccountSaveDTO accountSaveDTO);
    List<AccountSave> findAllByCustomerId(UUID customerId);
    List<AccountSave> findAll();
    AccountSave findById(UUID id);
}
