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
import rw.ac.rca.ne.starter.ne_starter.services.IMessageService;
import rw.ac.rca.ne.starter.ne_starter.services.ICustomerService;
import rw.ac.rca.ne.starter.ne_starter.utils.ExceptionUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static rw.ac.rca.ne.starter.ne_starter.utils.helpers.Helper.logAction;


@RestController
@RequestMapping("/api/v1/account-withdraws")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private  final IMessageService messageService;

    private Pageable pageable = null;


@GetMapping("/messages")
    public ResponseEntity<ApiResponse> findAll() {
        try {
            logAction(String.format("Request for getting all messages"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Messages fetched successfully",
                    messageService.findAll()
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/messages/{customerId}")
    public ResponseEntity<ApiResponse> findAllByCustomer(@PathVariable(value="customerId") UUID customerId) {
        try {
            logAction(String.format("Request for getting all messages by customer"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Messages fetched successfully",
                    messageService.findAllByCustomer(customerId)
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable(value="id") UUID id) {
        try {
            logAction(String.format("Request for getting message by id"));
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Message fetched successfully",
                    messageService.findById(id)
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }


}
