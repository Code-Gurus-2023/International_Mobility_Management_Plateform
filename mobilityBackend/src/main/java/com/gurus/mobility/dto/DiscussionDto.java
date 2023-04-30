package com.gurus.mobility.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DiscussionDto {
    private Long id;
    private String question;
    private Long views;
    private Set<CommentDto> comments = new HashSet<>();
}
