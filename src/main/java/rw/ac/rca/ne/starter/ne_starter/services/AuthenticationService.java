package rw.ac.rca.ne.starter.ne_starter.services;

import org.springframework.http.ResponseEntity;
import rw.ac.rca.ne.starter.ne_starter.dtos.requests.LoginDTO;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiResponse;


import java.util.UUID;

public interface AuthenticationService {
    ResponseEntity<ApiResponse> login(LoginDTO dto);
    ResponseEntity<ApiResponse> verifyAccount(String email, String code);


    ResponseEntity<ApiResponse> getProfileById(UUID id);

    ResponseEntity<ApiResponse> getProfile();



}
