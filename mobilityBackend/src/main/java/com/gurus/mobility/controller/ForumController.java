package com.gurus.mobility.controller;

import com.gurus.mobility.dto.DateCommentDto;
import com.gurus.mobility.dto.DiscussionUpdateDto;
import com.gurus.mobility.entity.ForumChat.Comment;
import com.gurus.mobility.entity.ForumChat.Discussion;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.StudentException;
import com.gurus.mobility.mapper.DiscussionMapper;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.ForumChatService.ICommentService;
import com.gurus.mobility.service.ForumChatService.IDiscussionService;
import com.gurus.mobility.service.User.IUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ICommentService commentService;

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
    public ResponseEntity updateDiscussion(@RequestBody DiscussionUpdateDto d/*, @RequestBody User user*/) {
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
            Discussion discussion = discussionService.getDiscussion(d.getId());
            discussion.setNameDsc(d.getContentDsc());

            if(user.getRoles().contains(ERole.ROLE_ETUDIANT))
                throw new StudentException("NOT A STUDENT");
            return new ResponseEntity<>(discussionService.updateDiscussion(discussion), HttpStatus.CREATED);
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
        }
        catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token expired");
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/activateDiscussion/{idDsc}")
    public ResponseEntity activateDsc(@PathVariable("idDsc") Long id) {

        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            //System.out.println(token);
            User user = userService.getUserByUsername(jwtUtils.
                    getUserNameFromJwtToken(token));

            if(discussionService.activateDiscussion(id, user.id))
                return ResponseEntity.status(HttpStatus.CREATED).body("Discussion activated");
            else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token expired");
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

    @GetMapping("/getDiscussionDetails/{idDsc}")
    public ResponseEntity getDisscussionDetails(@PathVariable("idDsc") Long idDsc) {
        Discussion discussion = discussionService.getDiscussionDetails(idDsc);

        if(discussion == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discussion Doesn't exist or is not activated");

        return ResponseEntity.status(HttpStatus.FOUND).body(DiscussionMapper.mapToDto(discussion));
    }

    @GetMapping("/getMostViewedDiscussions")
    public ResponseEntity getMostViewedDiscussions() {
        List<Discussion> discussions = discussionService.getMostViewedDiscussions();

        if(!discussions.isEmpty())
            return new ResponseEntity<>(discussions, HttpStatus.FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/getMostRespondedDate", produces = "application/json")
    @ResponseBody
    public ResponseEntity getMostViewedDate() {
        DateCommentDto datecomment = commentService.getMostCommentedDate();
        if(datecomment != null) {
            System.out.println(datecomment);
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(datecomment);
            return ResponseEntity.status(HttpStatus.FOUND).body(datecomment);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR");
    }
}
