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
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateBankAccountDTO;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiResponse;
import rw.ac.rca.ne.starter.ne_starter.services.IBankAccountService;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static rw.ac.rca.ne.starter.ne_starter.utils.helpers.Helper.logAction;


@RestController
@RequestMapping("/api/v1/bank-accounts")
@RequiredArgsConstructor
@Slf4j
public class BankAccountController {
    private  final IBankAccountService bankAccountService;

    private Pageable pageable = null;


    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> createBankAccount(@RequestBody CreateBankAccountDTO createBankAccountDTO) {
        try {
            logAction(String.format("Request for creating a Bank account"));
            return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Bank Account created successfully",
                            bankAccountService.createBankAccount(createBankAccountDTO)
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }


}

