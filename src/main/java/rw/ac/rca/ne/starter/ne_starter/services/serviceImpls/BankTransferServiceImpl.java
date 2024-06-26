package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.TransferDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.EBankOperation;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;
import rw.ac.rca.ne.starter.ne_starter.models.BankTransfer;
import rw.ac.rca.ne.starter.ne_starter.models.Message;
import rw.ac.rca.ne.starter.ne_starter.repositories.IBankAccountRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IBankTransferRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IMessageRepository;
import rw.ac.rca.ne.starter.ne_starter.services.IBankTransferService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BankTransferServiceImpl implements IBankTransferService {
    private final IBankAccountRepository bankAccountRepository;
    private final IBankTransferRepository bankTransferRepository;
    private final MailServiceImpl mailService;
    private final IMessageRepository messageRepository;
    @Override
    public BankTransfer transfer(TransferDTO transferDTO) {
        try{
            BankAccount fromAccount = bankAccountRepository.findById(transferDTO.getFromAccountId()).orElseThrow(()->new NotFoundException("Account not found"));
            BankAccount toAccount = bankAccountRepository.findById(transferDTO.getToAccountId()).orElseThrow(()->new NotFoundException("Account not found"));
            if(fromAccount.getBalance()<transferDTO.getAmount() || fromAccount.getBalance()==0 ||transferDTO.getAmount()==0){
                throw new NotFoundException("Insufficient balance or amount");
            }
            BankTransfer bankTransfer=new BankTransfer();
            bankTransfer.setFromAccount(fromAccount);
            bankTransfer.setToAccount(toAccount);
            bankTransfer.setAmount(transferDTO.getAmount());
            double newBalanceFrom=fromAccount.getBalance()-transferDTO.getAmount();
            double newBalanceTo=toAccount.getBalance()+transferDTO.getAmount();
            fromAccount.setBalance(newBalanceFrom);
            toAccount.setBalance(newBalanceTo);
            bankAccountRepository.save(fromAccount);
            bankAccountRepository.save(toAccount);
            BankTransfer bankTransfer1= bankTransferRepository.save(bankTransfer);
            if(bankTransfer1!=null){
                mailService.sendBankOperationEmail("Account Transfer",
                        bankTransfer1.getAmount(),
                        fromAccount.getAccountNumber(),
                        fromAccount.getAccountType().toString(),
                        fromAccount.getCustomer().getUser().getUsername(),
                        fromAccount.getCustomer().getUser().getEmail());
                messageRepository.save(new Message(fromAccount.getCustomer(), EBankOperation.TRANSFER,"You have transferred "+transferDTO.getAmount()+" to "+toAccount.getAccountNumber()));

                mailService.sendBankOperationEmail("Account Transfer",
                        bankTransfer1.getAmount(),
                        toAccount.getAccountNumber(),
                        toAccount.getAccountType().toString(),
                        toAccount.getCustomer().getUser().getUsername(),
                        toAccount.getCustomer().getUser().getEmail());
                messageRepository.save(new Message(toAccount.getCustomer(), EBankOperation.TRANSFER,"You have received "+transferDTO.getAmount()+" from "+fromAccount.getAccountNumber()));

            }
            return bankTransfer1;

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<BankTransfer> findAllByFromCustomerId(UUID customerId) {
        try {
            return bankTransferRepository.findAllByFromAccount_Customer_Id(customerId);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }



    @Override
    public List<BankTransfer> findAll() {
        try {
            return bankTransferRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public BankTransfer findById(UUID id) {
        try {
            return bankTransferRepository.findById(id).orElseThrow(()->new NotFoundException("Bank Transfer not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }


    @Override
    public List<BankTransfer> findAllByToCustomerId(UUID customerId) {
        try {
            return bankTransferRepository.findAllByFromAccount_Customer_Id(customerId);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }

    }
}
