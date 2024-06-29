package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.annotations.ValidDateOfBirth;
import rw.ac.rca.ne.starter.ne_starter.annotations.ValidGender;
import rw.ac.rca.ne.starter.ne_starter.enums.EGender;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO{
    @NotNull
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotNull
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Pattern(regexp = "^\\d{10}$", message = "Telephone number must be exactly 10 digits")
    private String phoneNumber;
    @Pattern(regexp = "^\\d{16}$", message = "National ID must be exactly 16 digits")
    private String nationalId;
    @NotNull(message = "Date of birth is mandatory")
    @ValidDateOfBirth
    private LocalDate dateOfBirth;

    @NotNull
    @ValidGender
    private EGender gender;

}