package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.dto.DateCommentDto;
import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.repository.ForumChatRepos.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;

    @Autowired
    private ResourceLoader resourceLoader;


    @Scheduled(cron = "0 0 23 * * *")
    public void exportComments() throws IOException {
        String fileName = "commentsLog.txt";
//        String filePath = "src/main/resources/" + fileName;
//        Resource resource = resourceLoader.getResource("file:" + filePath);
        Path path = Paths.get("src", "main", "resources", fileName);

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        List<Comment> comments = commentRepository.getCommentsByCreationDateCmtIs(startOfDay, endOfDay);

        String content = "";

        for (Comment c: comments) {
            content +=
                    c.getDiscussion().getNameDsc()
                    + ": " +c.getCreationDateCmt().toString()
                    + ": " + c.getContentCmt()
                    + ": " + c.getUpVoteCmt()
                    + ": " + c.getUser().userName + "\n"
            ;
        }

        //Path path = Paths.get(resource.getURI());
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }

        Files.writeString(path, content, StandardOpenOption.APPEND);

    }

    @Override
    public DateCommentDto getMostCommentedDate() {
//        for (DateCommentDto d: commentRepository.getMostCommentedDate()
//             ) {
//            System.out.println(d);
//        }
//        return commentRepository.getMostCommentedDate().get(0);

        List<Object[]> result = commentRepository.getMostCommentedDate();
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            BigInteger count =(BigInteger) row[1];
            return new DateCommentDto(date, count);
        } else {
            // handle empty result
            return null;
        }
    }

    @Override
    public List<DateCommentDto> getDateBynbreOfComments() {
        List<DateCommentDto> dateCommentDtos = new ArrayList<>();
        List<Object[]> results = commentRepository.getMostCommentedDate();

            for (Object[] result: results) {
                LocalDate date = ((java.sql.Date) result[0]).toLocalDate();
                BigInteger count = (BigInteger) result[1];
                dateCommentDtos.add(new DateCommentDto(date, count));
            }

            return dateCommentDtos;

    }
}
