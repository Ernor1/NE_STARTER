package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;

public interface ICustomerService {
    public Customer createCustomer(CreateCustomerDTO customerDTO);

}
