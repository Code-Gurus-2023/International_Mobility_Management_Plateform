package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.OfferRepository.ICommentaireRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.CommentaireService.ICommentaireService;
import com.gurus.mobility.service.OfferService.IOfferService;
import com.gurus.mobility.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireRestController {
    public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }
    @Autowired
    private IUserService iUserService;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private ICommentaireService commentaireService;

    @Autowired
    private ICommentaireRepository commentaireRepository;

    //    http://localhost:8080/espritmobility/api/commentaires/getCommentaires
    @GetMapping("/getCommentaires")
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    //    http://localhost:8080/espritmobility/api/commentaires/4
    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable Integer id) {
        return commentaireService.getCommentaireById(id);
    }

    //    http://localhost:8080/espritmobility/api/commentaires/createCommentaire
    @PostMapping("/createCommentaire")
    public ResponseEntity<Commentaire> createCommentaire(@Valid @RequestBody Commentaire commentaire) {
        Commentaire createdCommentaire = commentaireService.createCommentaire(commentaire);
        return new ResponseEntity<>(createdCommentaire, HttpStatus.CREATED);
    }

    //    http://localhost:8080/espritmobility/api/commentaires/5
    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Integer id, @Valid @RequestBody Commentaire commentaireDetails) {
        return commentaireService.updateCommentaire(id, commentaireDetails);
    }

    //    http://localhost:8080/espritmobility/api/commentaires/5
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Integer id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.noContent().build();
    }

    //    http://localhost:8080/espritmobility/api/commentaires/commentaires/bad-words
    @GetMapping("/commentaires/bad-words")
    public ResponseEntity<List<Boolean>> detecterBadWords() {
        List<Commentaire> commentaires = commentaireRepository.findAll();
        List<Boolean> badWordsDetected = new ArrayList<>();
        for (Commentaire commentaire : commentaires) {
            badWordsDetected.add(commentaireService.detecterBadWords(commentaire.getDescription()));
        }
        return ResponseEntity.ok().body(badWordsDetected);
    }

    //    http://localhost:8080/espritmobility/api/commentaires/2/archive
    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        commentaireService.archiveCommentaire(id);
    }

    // http://localhost:8080/espritmobility/api/commentaires/delayCommentaires
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE, value = "/delayCommentaires")
    public Flux<Commentaire> getAllCommentairesWithDelay() {
        Flux<Commentaire> commentaires = Flux.fromIterable(commentaireRepository.findAll());
        return commentaires.delayElements(Duration.ofMillis(10000));
    }

    @PostMapping("/ajouterCommentaire")
    public ResponseEntity ajouterCommentaire (@RequestBody Commentaire commentaire){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>("you should be connected",HttpStatus.FORBIDDEN);
        commentaireService.createCommentaire2(commentaire, user.getId());
        return new ResponseEntity<>("comment registred successefully", HttpStatus.OK);
    }



    @GetMapping("/myCommentaires")
    public ResponseEntity<List<Commentaire>> getAllMyCommentaires(){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(commentaireService.getCommentaireByUser(user.getId()),HttpStatus.OK);
    }

    @GetMapping("/UserCommentaires")
    public ResponseEntity<List<Commentaire>> getUserCommentaires(){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(commentaireService.getAllCommentaires(),HttpStatus.OK);
    }




}
