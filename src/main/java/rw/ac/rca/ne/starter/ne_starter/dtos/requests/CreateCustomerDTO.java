package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.annotations.Constraits.ValidDateOfBirth;
import rw.ac.rca.ne.starter.ne_starter.enums.EGender;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO{
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private String username;
    private String password;
    @Pattern(regexp = "^\\d{10}$", message = "Telephone number must be exactly 10 digits")
    private String phoneNumber;
    @Pattern(regexp = "^\\d{16}$", message = "National ID must be exactly 16 digits")
    private String nationalId;
    @NotNull(message = "Date of birth is mandatory")
    @ValidDateOfBirth
    private Date dateOfBirth;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
    private EGender gender;

}