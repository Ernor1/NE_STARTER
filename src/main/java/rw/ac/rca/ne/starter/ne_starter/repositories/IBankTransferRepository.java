package rw.ac.rca.ne.starter.ne_starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.BankTransfer;

import java.util.List;
import java.util.UUID;

public interface IBankTransferRepository extends JpaRepository<BankTransfer, UUID> {
    List<BankTransfer> findAllByFromAccount_Customer_Id(UUID customerId);
}
