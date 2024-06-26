package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAdminDTO;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.Roles;
import rw.ac.rca.ne.starter.ne_starter.models.Person;
import rw.ac.rca.ne.starter.ne_starter.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    public  boolean isUserLoggedIn();
    public User getLoggedInUser();
    public User getUserById(UUID uuid);
    public boolean isNotUnique(Person user);
    public boolean validateUserEntry(Person user);
    public List<User> findAll();
    public User findById(UUID id);
    public User saveUser(CreateCustomerDTO userDto, Roles role);
    public User createAdmin(CreateAdminDTO userDto);
}
