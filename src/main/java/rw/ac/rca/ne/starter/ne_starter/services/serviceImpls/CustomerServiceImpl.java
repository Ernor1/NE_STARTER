package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.Roles;
import rw.ac.rca.ne.starter.ne_starter.exceptions.BadRequestException;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;
import rw.ac.rca.ne.starter.ne_starter.models.Role;
import rw.ac.rca.ne.starter.ne_starter.models.User;
import rw.ac.rca.ne.starter.ne_starter.repositories.ICustomerRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IRoleRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IUserRepository;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;
import rw.ac.rca.ne.starter.ne_starter.utils.Hash;
import rw.ac.rca.ne.starter.ne_starter.utils.Utility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final IUserRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final MailServiceImpl mailService;
    private final IRoleRepository roleRepository;


    @Override
    public Customer createCustomer(CreateCustomerDTO customerDTO) {
        try {
           if(userRepository.findUserByEmail(customerDTO.getEmail()).isPresent()){
                throw new BadRequestException("Email already in use");
              }
            User user1= new User(customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getEmail(),customerDTO.getDateOfBirth(),customerDTO.getGender(),customerDTO.getPhoneNumber(),customerDTO.getNationalId(),customerDTO.getUsername(),Utility.generatedCode());
            user1.setPassword(Hash.hashPassword(customerDTO.getPassword()));
            Customer customer= new Customer();
            customer.setUser(user1);
            customer.setCustomerCode(Utility.generatedCode());
            Set<Role> roles= new HashSet<>();
            roles.add(roleRepository.findByRoleName(Roles.CUSTOMER.toString()));
            user1.setRoles(roles);
            userRepository.save(user1);
            customerRepository.save(customer);
            mailService.sendAccountVerificationEmail(user1);
            return customer;

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;

        }
    }

    @Override
    public Customer findById(UUID id) {
        try {
            return customerRepository.findById(id).orElseThrow(() -> new BadRequestException("Customer not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
    @Override
    public List<Customer> findAll() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

}
