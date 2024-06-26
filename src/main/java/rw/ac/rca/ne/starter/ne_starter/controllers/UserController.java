package rw.ac.rca.ne.starter.ne_starter.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAccountSaveDTO;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAdminDTO;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.enums.Roles;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiResponse;
import rw.ac.rca.ne.starter.ne_starter.services.AccountSaveService;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.services.serviceImpls.UserServiceImpl;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static rw.ac.rca.ne.starter.ne_starter.utils.helpers.Helper.logAction;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody CreateCustomerDTO createCustomerDTO,@RequestParam("role") Roles role) {
        try {
//            logAction(String.format("Request for creating a student with Full name :  %s , and email  :  %s", createCustomerDTO.getFirstName() + createCustomerDTO.getLastName() , createCustomerDTO.getEmail()));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "User created successfully",
                            userService.saveUser(createCustomerDTO,role)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }

    @PostMapping("/create-admin")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> createAdmin(@Valid @RequestBody CreateAdminDTO createAdminDTO) {
        try {
//            logAction(String.format("Request for creating a student with Full name :  %s , and email  :  %s", createCustomerDTO.getFirstName() + createCustomerDTO.getLastName() , createCustomerDTO.getEmail()));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Admin created successfully",
                            userService.createAdmin(createAdminDTO)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(){
        try {
            logAction(String.format("Request for getting all users"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Users fetched successfully",
                            userService.findAll()
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") UUID id){
        try {
            logAction(String.format("Request for getting a user with id : %s", id));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "User fetched successfully",
                            userService.findById(id)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
}

