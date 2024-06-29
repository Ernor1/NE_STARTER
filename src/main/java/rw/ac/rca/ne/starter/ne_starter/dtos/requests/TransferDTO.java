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
@NoArgsConstructor
@Getter
@Setter
public class TransferDTO  {
    @NotNull
    @NotBlank(message = "From account ID is mandatory")
    @ValidUUID
    private UUID fromAccountId;
    @NotNull
    @NotBlank(message = "To account ID is mandatory")
    @ValidUUID
    private UUID toAccountId;
    @NotNull
    @NotBlank(message = "Amount is mandatory")
    private double amount;

}
