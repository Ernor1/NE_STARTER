package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.rca.ne.starter.ne_starter.exceptions.BadRequestException;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.exceptions.UnAuthorizedException;
import rw.ac.rca.ne.starter.ne_starter.models.Person;
import rw.ac.rca.ne.starter.ne_starter.models.User;
import rw.ac.rca.ne.starter.ne_starter.repositories.IUserRepository;
import rw.ac.rca.ne.starter.ne_starter.security.user.UserSecurityDetails;
import rw.ac.rca.ne.starter.ne_starter.services.IUserService;


import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl  implements IUserService {

    private IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return false;
        } else {
            return true;
        }
    }

    public User getLoggedInUser() {
        UserSecurityDetails userSecurityDetails;
        // Retrieve the currently authenticated user from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            System.out.println("here");
            System.out.println(authentication.getPrincipal() );
            userSecurityDetails = (UserSecurityDetails) authentication.getPrincipal();
            return this.userRepository.findUserByEmail(userSecurityDetails.getUsername()).orElseThrow(() -> new UnAuthorizedException("You are not authorized! please login"));
        } else {
            throw new UnAuthorizedException("You are not authorized! please login");
        }
    }

    @Override
    public User getUserById(UUID uuid) {
        System.out.println(uuid);
        Optional<User> user =  userRepository.findById(uuid);
        if(user.isEmpty()) throw  new NotFoundException("The profile with the provided id is not found");
        return user.get();
    }

    public boolean isNotUnique(Person user) {
        return this.userRepository.findUserByEmail(user.getEmail()).isPresent();
    }

    public boolean validateUserEntry(Person user) {
        if (isNotUnique(user)) {
            String errorMessage = "The user with the email: " + user.getEmail() +
                    "  or national id: " + user.getNationalId() +
                    " or phone number: " + user.getPhoneNumber() + "" +
                    " already exists";
            throw new BadRequestException(errorMessage);
        } else {
            return true;
        }
    }
}
