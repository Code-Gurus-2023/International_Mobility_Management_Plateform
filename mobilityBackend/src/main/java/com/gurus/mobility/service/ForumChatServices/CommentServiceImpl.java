package com.gurus.mobility.service.ForumChatServices;

import com.gurus.mobility.repository.ForumChatRepos.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;
}
