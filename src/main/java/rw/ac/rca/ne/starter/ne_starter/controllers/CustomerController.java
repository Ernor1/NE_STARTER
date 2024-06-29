package rw.ac.rca.ne.starter.ne_starter.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiResponse;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static rw.ac.rca.ne.starter.ne_starter.utils.helpers.Helper.logAction;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CustomerController {
    private  final ICustomerService customerService;

    private Pageable pageable = null;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) {
        try {
            logAction(String.format("Request for creating a student with Full name :  %s , and email  :  %s", createCustomerDTO.getFirstName() + createCustomerDTO.getLastName() , createCustomerDTO.getEmail()));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Customer created successfully",
                            customerService.createCustomer(createCustomerDTO)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAllCustomers(){
        try {
            logAction(String.format("Request for getting all customers"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Customers fetched successfully",
                            customerService.findAll()
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable("id") UUID id){
        try {
            logAction(String.format("Request for getting a customer with id : %s", id));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Customer fetched successfully",
                            customerService.findById(id)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }




}

