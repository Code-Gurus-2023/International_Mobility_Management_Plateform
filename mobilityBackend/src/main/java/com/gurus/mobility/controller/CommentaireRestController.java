package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.repository.OfferRepository.ICommentaireRepository;
import com.gurus.mobility.service.CommentaireService.ICommentaireService;
import com.gurus.mobility.service.OfferService.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireRestController {
    @Autowired
    private ICommentaireService commentaireService;

    @Autowired
    private ICommentaireRepository commentaireRepository;

    @GetMapping("/getCommentaires")
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable Integer id) {
        return commentaireService.getCommentaireById(id);
    }

    @PostMapping("/createCommentaire")
    public ResponseEntity<Commentaire> createCommentaire(@Valid @RequestBody Commentaire commentaire) {
        Commentaire createdCommentaire = commentaireService.createCommentaire(commentaire);
        return new ResponseEntity<>(createdCommentaire, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Integer id, @Valid @RequestBody Commentaire commentaireDetails) {
        return commentaireService.updateCommentaire(id, commentaireDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Integer id) {
        commentaireService.deleteCommentaire(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/commentaires/bad-words")
    public ResponseEntity<List<Boolean>> detecterBadWords() {
        List<Commentaire> commentaires = commentaireRepository.findAll();
        List<Boolean> badWordsDetected = new ArrayList<>();
        for (Commentaire commentaire : commentaires) {
            badWordsDetected.add(commentaireService.detecterBadWords(commentaire.getDescription()));
        }
        return ResponseEntity.ok().body(badWordsDetected);
    }

}
