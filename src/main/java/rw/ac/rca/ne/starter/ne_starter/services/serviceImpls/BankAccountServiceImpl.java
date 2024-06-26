package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateBankAccountDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountStatus;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;
import rw.ac.rca.ne.starter.ne_starter.repositories.IBankAccountRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.ICustomerRepository;
import rw.ac.rca.ne.starter.ne_starter.services.IBankAccountService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;
import rw.ac.rca.ne.starter.ne_starter.utils.Utility;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    ICustomerRepository customerRepository;
    IBankAccountRepository bankAccountRepository;
    private final MailServiceImpl mailService;
    @Override
    public BankAccount createBankAccount(CreateBankAccountDTO createBankAccountDTO) {
        try{
            Customer customer= customerRepository.findById(createBankAccountDTO.getCustomerId()).orElseThrow(()->new NotFoundException("Customer not found"));
            BankAccount bankAccount=new BankAccount();
            bankAccount.setCustomer(customer);
            bankAccount.setAccountNumber(Utility.generateAccountNumber());
            bankAccount.setAccountType(createBankAccountDTO.getAccountType());
            bankAccount.setIsAccountActive(EAccountStatus.ACTIVE);
            bankAccount.setCurrency(createBankAccountDTO.getCurrency());
            bankAccount.setBalance(createBankAccountDTO.getBalance());
            bankAccount.setAccountType(createBankAccountDTO.getAccountType());
            if(createBankAccountDTO.getBalance()>0){
                mailService.sendBankOperationEmail("Account Depositing",createBankAccountDTO.getBalance(),bankAccount.getAccountNumber(),bankAccount.getAccountType().toString(),bankAccount.getCustomer().getUser().getUsername(),bankAccount.getCustomer().getUser().getEmail());
            }
            return bankAccountRepository.save(bankAccount);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public BankAccount findById(UUID id) {
        try {
            return bankAccountRepository.findById(id).orElseThrow(()->new NotFoundException("Bank Account not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;

        }
    }

    @Override
    public List<BankAccount> findAll() {
         try {
            return bankAccountRepository.findAll();
         }catch (Exception e){
             ExceptionUtils.handleServiceExceptions(e);
             return null;
         }
    }

    @Override
    public List<BankAccount> findAllByCustomerId(UUID customerId) {
        try {
            return bankAccountRepository.findByCustomer_Id(customerId);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }}
