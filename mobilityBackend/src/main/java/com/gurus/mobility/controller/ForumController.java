package com.gurus.mobility.controller;

import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.Role;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.StudentException;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.ForumChatService.IDiscussionService;
import com.gurus.mobility.service.User.IUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/api/forum")
public class ForumController {

    @Autowired
    IDiscussionService discussionService;

    @Autowired
    IUserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<Discussion>> getAllDiscussions() {
        List<Discussion> discussions = discussionService.getAll();

        if(discussions.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    //@PostMapping("/{idUser}")
    @PostMapping("addDiscussion")
    public ResponseEntity addDisscussion(@RequestBody Discussion d/*, @PathVariable("idUser") Long idUser*/) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //String token = (String) authentication.getCredentials();
//        User user = userService.getUserByUsername(jwtUtils.
//                getUserNameFromJwtToken((String) authentication.getCredentials()));




        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            //System.out.println(token);
            User user = userService.getUserByUsername(jwtUtils.
                    getUserNameFromJwtToken(token));
            //User u = userService.getUserById(idUser);


            if(user.getRoles().contains(ERole.ROLE_ETUDIANT))
                throw new StudentException("NOT A STUDENT");
            return new ResponseEntity<>(discussionService.addDiscussion(d, user), HttpStatus.CREATED);
        }
        catch (StudentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token expired");
        }
    }

    @PutMapping("updateDiscussion")
    public ResponseEntity updateDiscussion(@RequestBody Discussion d/*, @RequestBody User user*/) {
        try {

            //This block is for retreiving the user
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            //System.out.println(token);
            User user = userService.getUserByUsername(jwtUtils.
                    getUserNameFromJwtToken(token));
            //-------------------------


            if(user.getRoles().contains(ERole.ROLE_ETUDIANT))
                throw new StudentException("NOT A STUDENT");
            return new ResponseEntity<>(discussionService.updateDiscussion(d), HttpStatus.CREATED);
        }
        catch (StudentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token expired");
        }

        //return new ResponseEntity<>(discussionService.updateDiscussion(d), HttpStatus.CREATED);
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

    @PutMapping("/addComment/{idDiscussion}")
    public ResponseEntity addCommentToDiscussion(@RequestBody Comment c, @PathVariable("idDiscussion") Long idDiscussion/*,@RequestParam Long idUser*/) {

        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            User user = userService
                    .getUserByUsername(jwtUtils
                    .getUserNameFromJwtToken(token));


            //c.setUser(userService.getUserById(idUser));
            c.setUser(user);

            if(user.getRoles().contains(ERole.ROLE_ETUDIANT))
                throw new StudentException("NOT A STUDENT");

            discussionService.addComment(c, idDiscussion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment added");
        }
        catch (StudentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token expired");
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
