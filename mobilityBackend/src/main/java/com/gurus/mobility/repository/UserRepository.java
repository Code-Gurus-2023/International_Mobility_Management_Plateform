package com.gurus.mobility.repository;

import com.gurus.mobility.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface    UserRepository extends JpaRepository<User,Integer> {

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}
