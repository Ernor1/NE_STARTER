package rw.ac.rca.ne.starter.ne_starter.services;

import rw.ac.rca.ne.starter.ne_starter.models.Person;
import rw.ac.rca.ne.starter.ne_starter.models.User;

import java.util.UUID;

public interface IUserService {

    public  boolean isUserLoggedIn();
    public User getLoggedInUser();
    public User getUserById(UUID uuid);
    public boolean isNotUnique(Person user);
    public boolean validateUserEntry(Person user);
}
