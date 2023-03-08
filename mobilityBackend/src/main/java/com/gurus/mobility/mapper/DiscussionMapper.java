package com.gurus.mobility.mapper;

import com.gurus.mobility.dto.CommentDto;
import com.gurus.mobility.dto.DiscussionDto;
import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;

import java.util.HashSet;
import java.util.Set;

public class DiscussionMapper {

    public static DiscussionDto mapToDto(Discussion discussion) {

        Set<CommentDto> commentDtos = new HashSet<>();

        for (Comment c: discussion.getComments()) {
            commentDtos.add(
                    CommentDto.builder()
                            .idCmt(c.getIdCmt())
                            .contentCmt(c.getContentCmt())
                            .upVoteCmt(c.getUpVoteCmt())
                            .creationDate(c.getCreationDateCmt())
                            .username(c.getUser().userName)
                            .build()
            );
        }
        DiscussionDto discussionDto = DiscussionDto.builder()
                                        .id(discussion.getIdDsc())
                                        .views(discussion.getViewsDsc())
                                        .question(discussion.getNameDsc())
                                        .comments(commentDtos)
                                        .build();
        return discussionDto;
    }
}
