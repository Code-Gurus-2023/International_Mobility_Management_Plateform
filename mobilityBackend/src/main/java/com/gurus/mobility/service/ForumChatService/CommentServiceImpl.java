package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.repository.ForumChatRepos.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;
}
