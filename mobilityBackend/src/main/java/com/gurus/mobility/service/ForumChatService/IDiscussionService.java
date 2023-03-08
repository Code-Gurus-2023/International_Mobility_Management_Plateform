package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.user.User;

import java.util.List;

public interface IDiscussionService {

    Discussion addDiscussion(Discussion discussion, User u);

    void deactivateDiscussion(Long id);

    Discussion updateDiscussion(Discussion discussion);

    void addComment(Comment comment, Long idDiscussion);

    List<Discussion> getAll();

    List<Discussion> getMostRespondedDiscussions();

    Discussion getDiscussion(Long id);

    Discussion getDiscussionDetails(Long id);
    List<Discussion> getMostViewedDiscussions();
}
