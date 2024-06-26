package rw.ac.rca.ne.starter.ne_starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.AccountSave;

import java.util.List;
import java.util.UUID;

public interface IAccountSaveRepository extends JpaRepository<AccountSave, UUID>{
    List<AccountSave> findAllByAccount_Customer_Id(UUID customerId);
}
