package rw.ac.rca.ne.starter.ne_starter;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import rw.ac.rca.ne.starter.ne_starter.enums.Roles;
import rw.ac.rca.ne.starter.ne_starter.models.Role;
import rw.ac.rca.ne.starter.ne_starter.repositories.IRoleRepository;

import java.util.HashSet;
import java.util.Set;
@SpringBootApplication
@EnableCaching
@IgnoreForbiddenApisErrors(reason = "json")
@EnableWebSecurity
@EnableGlobalAuthentication()
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class NeStarterApplication {
    private final IRoleRepository roleRepository;

    public NeStarterApplication(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NeStarterApplication.class, args);
    }
    @Bean
    public void registerRoles(){
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(Roles.ADMIN);
        userRoles.add(Roles.CUSTOMER);
        userRoles.add(Roles.ACCOUNTANT);

        for(Roles role : userRoles ){
            Role sampleRole = new Role(role.toString());
            if(roleRepository.findByRoleName(role.name()) == null){
                roleRepository.save(sampleRole);
            }
        }
    }

}
