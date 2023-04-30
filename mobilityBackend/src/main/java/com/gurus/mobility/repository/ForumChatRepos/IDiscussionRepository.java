package com.gurus.mobility.repository.ForumChatRepos;

import com.gurus.mobility.entity.ForumChat.Discussion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("select d from Discussion d  order by d.nbrMessageDsc desc")
    List<Discussion> getMostRepliedDiscussions(Pageable pageable);

    @Query("select d from Discussion d order by d.viewsDsc desc")
    List<Discussion> getMostViewedDiscussions(Pageable pageable);


}
