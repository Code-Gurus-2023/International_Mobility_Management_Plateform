package com.gurus.mobility.service.CommentaireService;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.user.User;

import java.util.List;

public interface ICommentaireService {
    List<Commentaire> getAllCommentaires();
    Commentaire getCommentaireById(Integer id);
    Commentaire createCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire(Integer id, Commentaire commentaireDetails);
    void deleteCommentaire(Integer id);
    boolean detecterBadWords(String commentaire);
    void archiveCommentaire(Integer id);
    List<Commentaire> getCommentaireByUser(Long userId);

    void createCommentaire2(Commentaire commentaire, Long userId);
    boolean getUseridByCommentaireid(User user, int id);

}
