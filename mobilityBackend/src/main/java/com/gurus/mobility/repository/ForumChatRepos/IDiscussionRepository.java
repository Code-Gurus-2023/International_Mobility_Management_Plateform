package com.gurus.mobility.repository.ForumChatRepos;

import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.ForumChat.Custom.DiscussionCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("select d from Discussion d  order by count(d.comments)")
    List<Discussion> getMostRepliedDiscussions(Pageable pageable);
}
