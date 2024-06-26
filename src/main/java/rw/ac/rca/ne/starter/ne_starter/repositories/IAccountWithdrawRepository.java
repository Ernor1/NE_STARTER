package rw.ac.rca.ne.starter.ne_starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.AccountWithdraw;

import java.util.List;
import java.util.UUID;

public interface IAccountWithdrawRepository extends JpaRepository<AccountWithdraw, UUID> {
    List<AccountWithdraw> findAllByAccount_Customer_Id(UUID customerId);
}
