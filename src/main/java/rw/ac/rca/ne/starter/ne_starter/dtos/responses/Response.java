package rw.ac.rca.ne.starter.ne_starter.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rw.ac.rca.ne.starter.ne_starter.enums.ResponseType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
    private ResponseType type;
    private T payload;
}
