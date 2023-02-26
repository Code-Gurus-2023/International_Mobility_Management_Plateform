package com.gurus.mobility.repository.User;

import com.gurus.mobility.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface    UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByEmail(String email);
    Boolean existsByIdentifiant(String identifiant);
    Boolean existsByUserName(String userName);

    User findByUserName(String userName);
    User findByIdentifiant(String identifiant);

    User findByEmail(String email);

    User getUserByIdentifiant(String identifiant);
}
