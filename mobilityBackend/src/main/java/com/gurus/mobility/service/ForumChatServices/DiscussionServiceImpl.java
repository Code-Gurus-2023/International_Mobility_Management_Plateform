package com.gurus.mobility.service.ForumChatServices;

import com.gurus.mobility.repository.ForumChatRepos.IDiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionServiceImpl implements IDiscussionService{

    @Autowired
    IDiscussionRepository discussionRepository;
}
