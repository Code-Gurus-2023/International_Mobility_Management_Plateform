package com.gurus.mobility.repository.ForumChatRepos;

import com.gurus.mobility.entity.ForumChat.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

//    @Query("SELECT c FROM Comment c WHERE DATE(c.creationDateCmt) = DATE(:date) - INTERVAL 1 DAY")
//    List<Comment> getCommentsByCreationDateCmtIs(LocalDateTime date);

    @Query("SELECT c FROM Comment c WHERE c.creationDateCmt BETWEEN :startDate AND :endDate")
    List<Comment> getCommentsByCreationDateCmtIs(LocalDateTime startDate, LocalDateTime endDate);

}

