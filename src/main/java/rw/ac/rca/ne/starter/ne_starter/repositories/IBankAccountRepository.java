package rw.ac.rca.ne.starter.ne_starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;

import java.util.UUID;

public interface IBankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
