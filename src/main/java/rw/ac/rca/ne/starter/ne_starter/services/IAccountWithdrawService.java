package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.WithDrawDTO;
import rw.ac.rca.ne.starter.ne_starter.models.AccountWithdraw;

import java.util.List;
import java.util.UUID;

public interface IAccountWithdrawService {
    AccountWithdraw withdraw(WithDrawDTO withDrawDTO);
    List<AccountWithdraw> findAllByCustomerId(UUID customerId);
    List<AccountWithdraw> findAll();
    AccountWithdraw findById(String id);
}
