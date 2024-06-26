package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.models.BankAccount;

import javax.validation.constraints.NotNull;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAccountSaveDTO {
    @NotNull
    private UUID accountId;
    private double amount;
}
