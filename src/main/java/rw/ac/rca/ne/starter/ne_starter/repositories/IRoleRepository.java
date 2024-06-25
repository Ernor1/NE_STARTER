package rw.ac.rca.ne.starter.ne_starter.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.ne.starter.ne_starter.models.Role;

import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Role findByRoleName(String roleName);
}
