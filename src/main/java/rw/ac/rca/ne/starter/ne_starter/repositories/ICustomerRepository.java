package rw.ac.rca.ne.starter.ne_starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;

import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {
}
