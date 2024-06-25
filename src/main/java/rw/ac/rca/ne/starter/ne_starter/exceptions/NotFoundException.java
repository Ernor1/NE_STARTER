package rw.ac.rca.ne.starter.ne_starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import rw.ac.rca.ne.starter.ne_starter.dtos.responses.ErrorResponse;
import rw.ac.rca.ne.starter.ne_starter.dtos.responses.Response;
import rw.ac.rca.ne.starter.ne_starter.enums.ResponseType;
import rw.ac.rca.ne.starter.ne_starter.payload.ApiSecondResponse;


import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

    public ResponseEntity<ApiSecondResponse> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.RESOURCE_NOT_FOUND);
        response.setPayload(errorResponse);
        return  ResponseEntity.ok(ApiSecondResponse.fail(this.getMessage()));
    }
}