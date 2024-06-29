package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.annotations.ValidAccountType;
import rw.ac.rca.ne.starter.ne_starter.annotations.ValidUUID;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountStatus;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountType;
import rw.ac.rca.ne.starter.ne_starter.enums.ECurrency;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankAccountDTO {
    @NotNull
    @ValidAccountType
    private EAccountType accountType;
    @NotNull
    @NotBlank(message = "Account number is mandatory")
    private double balance;
    @NotNull
    @NotBlank(message = "Currency is mandatory")
    private ECurrency currency;
    @NotNull
    @NotBlank(message = "Customer ID is mandatory")
    @ValidUUID
    private UUID customerId;


}
