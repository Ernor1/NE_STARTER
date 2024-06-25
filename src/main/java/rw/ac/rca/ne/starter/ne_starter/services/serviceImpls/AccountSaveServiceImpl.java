package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAccountSaveDTO;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.models.AccountSave;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;
import rw.ac.rca.ne.starter.ne_starter.repositories.IAccountSaveRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IBankAccountRepository;
import rw.ac.rca.ne.starter.ne_starter.services.AccountSaveService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;

@Service
@AllArgsConstructor
public class AccountSaveServiceImpl implements AccountSaveService {
    private final IBankAccountRepository bankAccountRepository;
    private final IAccountSaveRepository accountSaveRepository;
    @Override
    public AccountSave save(CreateAccountSaveDTO accountSaveDTO) {
        try{
            BankAccount account= bankAccountRepository.findById(accountSaveDTO.getAccountId()).orElseThrow(()->new NotFoundException("Account not found"));
            AccountSave accountSave=new AccountSave();
            accountSave.setAccount(account);
            accountSave.setAmount(accountSaveDTO.getAmount());
            double newBalance=account.getBalance()+accountSaveDTO.getAmount();
            account.setBalance(newBalance);
            bankAccountRepository.save(account);
            return accountSaveRepository.save(accountSave);

        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
