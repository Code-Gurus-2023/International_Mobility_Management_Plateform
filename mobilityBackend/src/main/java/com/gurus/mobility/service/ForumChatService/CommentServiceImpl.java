package com.gurus.mobility.service.ForumChatService;

import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.repository.ForumChatRepos.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
}
