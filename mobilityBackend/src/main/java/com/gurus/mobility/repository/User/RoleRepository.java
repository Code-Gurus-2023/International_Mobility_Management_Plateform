package com.gurus.mobility.repository.User;

import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
