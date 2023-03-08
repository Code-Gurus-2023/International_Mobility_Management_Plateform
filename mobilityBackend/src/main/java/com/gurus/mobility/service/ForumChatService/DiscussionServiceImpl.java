package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.ForumChatRepos.ICommentRepository;
import com.gurus.mobility.repository.ForumChatRepos.IDiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscussionServiceImpl implements IDiscussionService{

    @Autowired
    IDiscussionRepository discussionRepository;

    @Autowired
    ICommentRepository commentRepository;

    @Override
    public Discussion addDiscussion(Discussion discussion, User user) {

        discussion.setUser(user);
        discussion.setNbrMessageDsc(0);
        discussion.setViewsDsc(0L);
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
    @Transactional
    public void addComment(Comment c, Long idDiscussion) {

        Discussion d = discussionRepository.findById(idDiscussion).orElse(null);
        //d.getComments().add(c);
        c.setDiscussion(d);
        c.setCreationDateCmt(LocalDateTime.now());
        c.setUpVoteCmt(0L);
        d.incrementMessages();
        d.setLastMessageDate(c.getCreationDateCmt());
        //discussionRepository.save(d);
        commentRepository.save(c);
        discussionRepository.save(d);
    }

    @Override
    public List<Discussion> getAll() {
        return discussionRepository.findAll();
    }

    @Override
    public List<Discussion> getMostRespondedDiscussions() {

        //return discussionRepository.getMostRepliedDiscussions(PageRequest.of(0, 3));

        return discussionRepository.getMostRepliedDiscussions(PageRequest.of(0, 3));

    }

    @Override
    public Discussion getDiscussion(Long id) {

        Discussion discussion = discussionRepository.findById(id).get();
//        if(discussion != null){
//            discussion.incrementViews();
//            discussionRepository.save(discussion);
//        }
        return discussion;
    }

    @Override
    public Discussion getDiscussionDetails(Long id) {

        Discussion discussion = discussionRepository.findById(id).get();
        if(!discussion.getIsActive())
            return null;

        discussion.incrementViews();
        discussionRepository.save(discussion);

        return discussion;
    }

    @Override
    public List<Discussion> getMostViewedDiscussions() {
        return discussionRepository.getMostViewedDiscussions(PageRequest.of(0, 5));
    }

    @Override
    public Boolean activateDiscussion(Long id, Long idUser) {
        Discussion d = discussionRepository.
                findById(id).orElse(null);

        if(d.getUser().id == idUser) {
            d.setIsActive(true);
            discussionRepository.save(d);

            return true;
        }

        return false;

    }


}
