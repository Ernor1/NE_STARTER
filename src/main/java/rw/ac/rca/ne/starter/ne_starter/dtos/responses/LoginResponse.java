package rw.ac.rca.ne.starter.ne_starter.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rw.ac.rca.ne.starter.ne_starter.models.Role;
import rw.ac.rca.ne.starter.ne_starter.models.User;


import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    public String token;
    public User user;
    public Set<Role> roles;
}
