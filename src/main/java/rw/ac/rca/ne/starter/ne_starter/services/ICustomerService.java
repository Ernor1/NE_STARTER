package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {
    public Customer createCustomer(CreateCustomerDTO customerDTO);
    public Customer findById(UUID id);
    public List<Customer> findAll();

}
