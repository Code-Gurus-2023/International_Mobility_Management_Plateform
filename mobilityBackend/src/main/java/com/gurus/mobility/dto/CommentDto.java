package com.gurus.mobility.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {

    private Long idCmt;
    private Long upVoteCmt;
    private String contentCmt;
    private String username;
    private LocalDateTime creationDate;
}
