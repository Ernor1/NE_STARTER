package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.TransferDTO;
import rw.ac.rca.ne.starter.ne_starter.models.BankTransfer;

import java.util.List;
import java.util.UUID;

public interface IBankTransferService {
    BankTransfer transfer(TransferDTO transferDTO);
    List<BankTransfer> findAllByFromCustomerId(UUID customerId);
    List<BankTransfer> findAll();
    BankTransfer findById(UUID id);
    List<BankTransfer> findAllByToCustomerId(UUID customerId);
}
