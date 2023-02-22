package com.gurus.mobility.service.CommentaireService;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.repository.OfferRepository.ICommentaireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class CommentaireServiceImpl implements ICommentaireService{
    @Autowired
    private ICommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getCommentaireById(Integer id) {
        return commentaireRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commentaire not found with id " + id));
    }

    @Override
    public Commentaire createCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }


    @Override
    public Commentaire updateCommentaire(Integer id, Commentaire commentaireDetails) {
        Commentaire commentaire = getCommentaireById(id);
        commentaire.setDescription(commentaireDetails.getDescription());
        commentaire.setCreationDate(commentaireDetails.getCreationDate());
        return commentaireRepository.save(commentaire);
    }


    @Override
    public void deleteCommentaire(Integer id) {
        commentaireRepository.deleteById(id);
    }


}