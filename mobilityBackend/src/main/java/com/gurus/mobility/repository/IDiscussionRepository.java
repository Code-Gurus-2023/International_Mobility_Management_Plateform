package com.gurus.mobility.repository;

import com.gurus.mobility.entity.ForumChat.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiscussionRepository extends JpaRepository<Discussion, Long> {
}
