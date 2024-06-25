package rw.ac.rca.ne.starter.ne_starter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountStatus;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountType;
import rw.ac.rca.ne.starter.ne_starter.enums.ECurrency;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String accountNumber;
    private EAccountType accountType;
    private double balance=0.0;
    private ECurrency currency;
    private EAccountStatus isAccountActive;
    @ManyToOne
    private Customer customer;


}
