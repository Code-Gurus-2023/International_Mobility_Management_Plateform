package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.Role;

import java.util.Optional;

public interface IRoleService {

    Optional<Role> findByName(ERole name);
}
