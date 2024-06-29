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
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateAccountSaveDTO;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiResponse;
import rw.ac.rca.ne.starter.ne_starter.services.AccountSaveService;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static rw.ac.rca.ne.starter.ne_starter.utils.helpers.Helper.logAction;


@RestController
@RequestMapping("/api/v1/account-saves")
@RequiredArgsConstructor
@Slf4j
public class AccountSaveController {
    private  final AccountSaveService accountSaveService;

    private Pageable pageable = null;


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> createAccountSave(@RequestBody CreateAccountSaveDTO createAccountSaveDTO) {
        try {
            logAction(String.format("Request for Saving amount in Bank account"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Account Deposit successfully",
                            accountSaveService.save(createAccountSaveDTO
                    ))
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAllAccountSaves() {
        try {
            logAction(String.format("Request for getting all Account saves"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Account saves retrieved successfully",
                            accountSaveService.findAll()
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAllAccountSavesByCustomerId(@PathVariable UUID customerId) {
        try {
            logAction(String.format("Request for getting all Account saves by customer id"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Account saves retrieved successfully",
                            accountSaveService.findAllByCustomerId(customerId)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')  or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAccountSaveById(@PathVariable UUID id) {
        try {
            logAction(String.format("Request for getting Account save by id"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Account save retrieved successfully",
                            accountSaveService.findById(id)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }



}

