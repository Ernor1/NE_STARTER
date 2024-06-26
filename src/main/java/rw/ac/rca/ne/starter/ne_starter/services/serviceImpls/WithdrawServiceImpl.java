package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.WithDrawDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.EBankOperation;
import rw.ac.rca.ne.starter.ne_starter.exceptions.BadRequestException;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.models.AccountWithdraw;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;
import rw.ac.rca.ne.starter.ne_starter.models.Message;
import rw.ac.rca.ne.starter.ne_starter.repositories.IAccountWithdrawRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IBankAccountRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IMessageRepository;
import rw.ac.rca.ne.starter.ne_starter.services.IAccountWithdrawService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class WithdrawServiceImpl implements IAccountWithdrawService {
    private final IBankAccountRepository bankAccountRepository;
    private final IAccountWithdrawRepository accountWithdrawRepository;
    private final MailServiceImpl mailService;
    private final IMessageRepository messageRepository;
    @Override
    public AccountWithdraw withdraw(WithDrawDTO withDrawDTO) {
        try{
            BankAccount bankAccount= bankAccountRepository.findById(withDrawDTO.getAccountId()).orElseThrow(()->new NotFoundException("Account not found"));
            if(bankAccount.getBalance()<withDrawDTO.getAmount() || bankAccount.getBalance()==0 ||withDrawDTO.getAmount()==0){
                throw new BadRequestException("Insufficient balance");
            }
            AccountWithdraw accountWithdraw=new AccountWithdraw();
            accountWithdraw.setAccount(bankAccount);
            accountWithdraw.setAmount(withDrawDTO.getAmount());
            double newBalance=bankAccount.getBalance()-withDrawDTO.getAmount();
            bankAccount.setBalance(newBalance);
            bankAccountRepository.save(bankAccount);
            AccountWithdraw accountWithdraw1= accountWithdrawRepository.save(accountWithdraw);
            if(accountWithdraw1!=null){
                mailService.sendBankOperationEmail("Account Withdrawal",
                        accountWithdraw1.getAmount(),
                        bankAccount.getAccountNumber(),
                        bankAccount.getAccountType().toString(),
                        bankAccount.getCustomer().getUser().getUsername(),
                        bankAccount.getCustomer().getUser().getEmail());
                messageRepository.save(new Message(bankAccount.getCustomer(), EBankOperation.WITHDRAWAL,"You have withdrawn "+withDrawDTO.getAmount()+" from your account"));
            }
            return accountWithdraw1;

        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<AccountWithdraw> findAllByCustomerId(UUID customerId) {
        try {
            return accountWithdrawRepository.findAllByAccount_Customer_Id(customerId);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<AccountWithdraw> findAll() {
        try {
            return accountWithdrawRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public AccountWithdraw findById(String id) {
        try {
            return accountWithdrawRepository.findById(UUID.fromString(id)).orElseThrow(()->new NotFoundException("Account not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
