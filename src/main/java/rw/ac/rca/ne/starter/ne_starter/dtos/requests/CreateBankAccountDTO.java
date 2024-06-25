package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountStatus;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountType;
import rw.ac.rca.ne.starter.ne_starter.enums.ECurrency;
import rw.ac.rca.ne.starter.ne_starter.models.Customer;

import javax.persistence.ManyToOne;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankAccountDTO {
    private EAccountType accountType;
    private double balance;
    private ECurrency currency;
    private UUID customerId;


}
