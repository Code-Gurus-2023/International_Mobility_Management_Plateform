package com.gurus.mobility.repository.User;

import com.gurus.mobility.entity.user.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    FileDB findByName(String name);
}
