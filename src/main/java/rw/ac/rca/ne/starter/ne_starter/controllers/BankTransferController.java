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
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.TransferDTO;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiResponse;
import rw.ac.rca.ne.starter.ne_starter.services.IBankTransferService;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static rw.ac.rca.ne.starter.ne_starter.utils.helpers.Helper.logAction;


@RestController
@RequestMapping("/api/v1/bank-transfers")
@RequiredArgsConstructor
@Slf4j
public class BankTransferController {
    private  final IBankTransferService accountTransferService;

    private Pageable pageable = null;


    @PostMapping("/transfer")
   @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> transfer(@RequestBody TransferDTO transferDto) {
        try {
            logAction(String.format("Request for Transferring amount in Bank account"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Account Transfer successfully",

                    accountTransferService.transfer(transferDto
                    ))
            );
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAllAccountTransfers() {
        try {
            logAction(String.format("Request for getting all Bank account transfers"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Account Transfers fetched successfully",
                    accountTransferService.findAll()
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    @GetMapping("/from-customer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAllAccountTransfersByCustomerId(@PathVariable UUID customerId) {
        try {
            logAction(String.format("Request for getting all Account transfers by customer id"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Account transfers fetched successfully",
                    accountTransferService.findAllByFromCustomerId(customerId)
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAccountTransferById(@PathVariable UUID id) {
        try {
            logAction(String.format("Request for getting Account transfer by id"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Account transfer fetched successfully",
                    accountTransferService.findById(id)
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    @GetMapping("/to-customer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('CUSTOMER') or hasAuthority('MANAGER')")
    public ResponseEntity<ApiResponse> getAllAccountTransfersToCustomerId(@PathVariable UUID customerId) {
        try {
            logAction(String.format("Request for getting all Account transfers by customer id"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Account transfers fetched successfully",
                    accountTransferService.findAllByToCustomerId(customerId)
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }


}
