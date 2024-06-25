package rw.ac.rca.ne.starter.ne_starter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.audits.Initializer;
import rw.ac.rca.ne.starter.ne_starter.enums.EBankOperation;

import javax.persistence.*;
import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountSave extends Initializer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private BankAccount account;
    private double amount;
    private EBankOperation operation=EBankOperation.SAVE;
}
