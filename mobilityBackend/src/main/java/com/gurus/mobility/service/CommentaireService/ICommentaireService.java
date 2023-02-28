package com.gurus.mobility.service.CommentaireService;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;

import java.util.List;

public interface ICommentaireService {
    List<Commentaire> getAllCommentaires();
    Commentaire getCommentaireById(Integer id);
    Commentaire createCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire(Integer id, Commentaire commentaireDetails);
    void deleteCommentaire(Integer id);
    boolean detecterBadWords(String commentaire);
}
