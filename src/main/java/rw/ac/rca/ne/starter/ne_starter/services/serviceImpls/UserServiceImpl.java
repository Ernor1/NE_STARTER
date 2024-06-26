package rw.ac.rca.ne.starter.ne_starter.services.serviceImpls;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAdminDTO;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.Roles;
import rw.ac.rca.ne.starter.ne_starter.exceptions.BadRequestException;
import rw.ac.rca.ne.starter.ne_starter.exceptions.NotFoundException;
import rw.ac.rca.ne.starter.ne_starter.exceptions.UnAuthorizedException;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;
import rw.ac.rca.ne.starter.ne_starter.models.Person;
import rw.ac.rca.ne.starter.ne_starter.models.Role;
import rw.ac.rca.ne.starter.ne_starter.models.User;
import rw.ac.rca.ne.starter.ne_starter.repositories.IRoleRepository;
import rw.ac.rca.ne.starter.ne_starter.repositories.IUserRepository;
import rw.ac.rca.ne.starter.ne_starter.security.user.UserSecurityDetails;
import rw.ac.rca.ne.starter.ne_starter.services.IUserService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;
import rw.ac.rca.ne.starter.ne_starter.utils.Hash;
import rw.ac.rca.ne.starter.ne_starter.utils.Utility;


import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl  implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final MailServiceImpl mailService;



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
            System.out.println(authentication.getAuthorities().size());
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

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public User findById(UUID id) {
        try {
            return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public User saveUser(CreateCustomerDTO userDto, Roles role) {
        try {
            if(userRepository.findUserByEmail(userDto.getEmail()).isPresent()){
                throw new BadRequestException("Email already in use");
            }
            User user1= new User(userDto.getFirstName(),userDto.getLastName(),userDto.getEmail(),userDto.getDateOfBirth(),userDto.getGender(),userDto.getPhoneNumber(),userDto.getNationalId(),userDto.getUsername(), Utility.generatedCode());
            user1.setPassword(Hash.hashPassword(userDto.getPassword()));

            Set<Role> roles= new HashSet<>();
            roles.add(roleRepository.findByRoleName(role.toString()));
            user1.setRoles(roles);
           User user= userRepository.save(user1);
           if(user !=null){
               mailService.sendAccountVerificationEmail(user1);

           }
            return user;

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;

        }
    }

    @Override
    public User createAdmin(CreateAdminDTO userDto) {
        try{
            if(userRepository.findUserByEmail(userDto.getEmail()).isPresent()){
                throw new BadRequestException("Email already in use");
            }
            if(userDto.getAdminKey().equals("admin123")){
                User user1= new User(userDto.getFirstName(),userDto.getLastName(),userDto.getEmail(),userDto.getDateOfBirth(),userDto.getGender(),userDto.getPhoneNumber(),userDto.getNationalId(),userDto.getUsername(),Utility.generatedCode());
                user1.setPassword(Hash.hashPassword(userDto.getPassword()));
                Set<Role> roles= new HashSet<>();
                roles.add(roleRepository.findByRoleName(Roles.ADMIN.toString()));
                user1.setRoles(roles);
                User user= userRepository.save(user1);
                if(user !=null){
                    mailService.sendAccountVerificationEmail(user1);

                }
                return user;

            }else{
                throw new BadRequestException("Invalid admin key");
            }

        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

}
