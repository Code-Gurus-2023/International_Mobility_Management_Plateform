package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.Role;
import com.gurus.mobility.repository.User.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class InitRoles {
    @Autowired
    private RoleRepository roleRepository;
    @PostConstruct
    public void createRolesInDatabse(){
        if(roleRepository.findAll().isEmpty()){
            Role roleAdmine =new Role(ERole.ROLE_ADMIN);
            Role roleEtudiant =new Role(ERole.ROLE_ETUDIANT);
            Role roleEnseignant =new Role(ERole.ROLE_ENSEIGNANT);
            Role roleUniversity =new Role(ERole.ROLE_UNIVERSITE);
            Role roleProprietaire =new Role(ERole.OWNER);
            List<Role> roles= Arrays.asList(roleAdmine,roleEtudiant,roleEnseignant,roleUniversity,roleProprietaire);
            roleRepository.saveAll(roles);
        }
    }
}