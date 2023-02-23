package com.gurus.mobility.controller;

import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.Role;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.StudentException;
import com.gurus.mobility.service.ForumChatService.IDiscussionService;
import com.gurus.mobility.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/forum")
public class ForumController {

    @Autowired
    IDiscussionService discussionService;

    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<List<Discussion>> getAllDiscussions() {
        List<Discussion> discussions = discussionService.getAll();

        if(discussions.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<Discussion> addDisscussion(@RequestBody Discussion d, @PathVariable("idUser") Integer idUser) {
        try {

            User u = userService.findById(idUser);
            if(!u.getRoles().contains(ERole.ROLE_ETUDIANT))
                throw new StudentException("NOT A STUDENT");
            return new ResponseEntity<>(discussionService.addDiscussion(d, u), HttpStatus.CREATED);
        }
        catch (StudentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping
    public ResponseEntity<Discussion> updateDiscussion(@RequestBody Discussion d/*, @RequestBody User user*/) {
        /*try {
            if(!user.getRoles().contains(ERole.ROLE_ETUDIANT))
                throw new StudentException("NOT A STUDENT");
            return new ResponseEntity<>(discussionService.updateDiscussion(d), HttpStatus.CREATED);
        }
        catch (StudentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }*/

        return new ResponseEntity<>(discussionService.updateDiscussion(d), HttpStatus.CREATED);
    }

    @DeleteMapping("{idDiscussion}")
    public ResponseEntity deactivateDiscussion(@PathVariable("idDiscussion") Long id/*, @RequestBody User user*/) {
        try {
            discussionService.deactivateDiscussion(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Discussion deactivated");
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addComment")
    public ResponseEntity addCommentToDiscussion(@RequestBody Comment c, @RequestParam Long idDiscussion,@RequestParam Integer idUser) {

        try {
            c.setUser(userService.findById(idUser));
            discussionService.addComment(c, idDiscussion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment added");
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMostRepliedDiscussions")
    public ResponseEntity<List<Discussion>> getMostRepliedDiscussions() {
        List<Discussion> discussions = discussionService.getMostRespondedDiscussions();

        if(!discussions.isEmpty())
            return new ResponseEntity<>(discussions, HttpStatus.FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
