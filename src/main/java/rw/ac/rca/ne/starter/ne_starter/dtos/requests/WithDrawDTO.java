package rw.ac.rca.ne.starter.ne_starter.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.annotations.ValidUUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class WithDrawDTO {
    @NotNull
    @NotBlank(message = "Account ID is mandatory")
    @ValidUUID
    private UUID accountId;
    @NotNull
    @NotBlank(message = "Amount is mandatory")
    private double amount;
}
