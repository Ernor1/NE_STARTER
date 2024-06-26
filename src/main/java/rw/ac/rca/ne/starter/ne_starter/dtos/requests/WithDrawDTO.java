package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class WithDrawDTO {
    private UUID accountId;
    private double amount;
}
