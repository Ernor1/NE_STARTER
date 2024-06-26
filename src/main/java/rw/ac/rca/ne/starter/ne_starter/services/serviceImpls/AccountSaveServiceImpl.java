package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAccountSaveDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.EBankOperation;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.models.AccountSave;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;
import rw.ac.rca.ne.starter.ne_starter.models.Message;
import rw.ac.rca.ne.starter.ne_starter.repositories.IAccountSaveRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IBankAccountRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IMessageRepository;
import rw.ac.rca.ne.starter.ne_starter.services.AccountSaveService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountSaveServiceImpl implements AccountSaveService {
    private final IBankAccountRepository bankAccountRepository;
    private final IAccountSaveRepository accountSaveRepository;
    private final MailServiceImpl mailService;
    private final IMessageRepository messageRepository;
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
            AccountSave accountSave1=accountSaveRepository.save(accountSave);
            if(accountSave1!=null){
                mailService.sendBankOperationEmail("Account Depositing",
                        accountSaveDTO.getAmount(),
                        account.getAccountNumber(),
                        account.getAccountType().toString(),
                        account.getCustomer().getUser().getUsername(),
                        account.getCustomer().getUser().getEmail());
                messageRepository.save(new Message(account.getCustomer(),EBankOperation.DEPOSIT,"You have deposited "+accountSaveDTO.getAmount()+" in your account"));
            }

            return accountSave1;

        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<AccountSave> findAllByCustomerId(UUID customerId) {
        try {
            return accountSaveRepository.findAllByAccount_Customer_Id(customerId);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<AccountSave> findAll() {
       try {
              return accountSaveRepository.findAll();
         } catch (Exception e) {
              ExceptionUtils.handleServiceExceptions(e);
              return null;
       }
    }

    @Override
    public AccountSave findById(UUID id) {
        try {
            return accountSaveRepository.findById(id).orElseThrow(()->new NotFoundException("Account not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
