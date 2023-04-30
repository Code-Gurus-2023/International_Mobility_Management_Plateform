package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.dto.DateCommentDto;

import java.util.List;

public interface ICommentService {

    DateCommentDto getMostCommentedDate();
    List<DateCommentDto> getDateBynbreOfComments();
}
