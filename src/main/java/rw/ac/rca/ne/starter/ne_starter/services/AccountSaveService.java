package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAccountSaveDTO;
import rw.ac.rca.ne.starter.ne_starter.models.AccountSave;

public interface AccountSaveService {
    AccountSave save(CreateAccountSaveDTO accountSaveDTO);
}
