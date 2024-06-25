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

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    ICustomerRepository customerRepository;
    IBankAccountRepository bankAccountRepository;
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
            return bankAccountRepository.save(bankAccount);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
