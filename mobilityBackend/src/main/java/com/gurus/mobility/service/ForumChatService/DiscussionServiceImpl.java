package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.ForumChatRepos.IDiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements IDiscussionService{

    @Autowired
    IDiscussionRepository discussionRepository;

    @Override
    public Discussion addDiscussion(Discussion discussion, User user) {

        discussion.setUser(user);
        discussion.setIsActive(true);
        return discussionRepository.save(discussion);
    }

    @Override
    public void deactivateDiscussion(Long id) {

        Discussion d = discussionRepository.findById(id).orElse(null);
        d.setIsActive(false);
        discussionRepository.save(d);
    }

    @Override
    public Discussion updateDiscussion(Discussion d) {
        return discussionRepository.save(d);
    }

    @Override
    public Discussion addComment(Comment c, Long idDiscussion) {
        Discussion d = discussionRepository.findById(idDiscussion).orElse(null);
        d.getComments().add(c);
        return discussionRepository.save(d);
    }

    @Override
    public List<Discussion> getAll() {
        return discussionRepository.findAll();
    }

    @Override
    public List<Discussion> getMostRespondedDiscussions() {

        return discussionRepository.getMostRepliedDiscussions(PageRequest.of(0, 3));

    }


}
