package rw.ac.rca.ne.starter.ne_starter.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rw.ac.rca.ne.starter.ne_starter.models.Role;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class JwtUserInfo {
    private String userId;
    private String email;
    private List<Role> role;
}

