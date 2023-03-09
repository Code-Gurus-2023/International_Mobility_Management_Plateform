package com.gurus.mobility.service.CommentaireService;

import com.gurus.mobility.entity.Offer.Commentaire;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.OfferRepository.ICommentaireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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

    private static final List<String> BAD_WORDS = Arrays.asList("badword1", "badword2", "badword3");

    @Override
    public boolean detecterBadWords(String commentaire) {
        for (String badWord : BAD_WORDS) {
            if (commentaire.toLowerCase().contains(badWord)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void archiveCommentaire(Integer id) {
        Commentaire commentaire = getCommentaireById(id);
        commentaireRepository.delete(commentaire);

        try {
            FileWriter fileWriter = new FileWriter("C:/Code Gurus 2023/International_Mobility_Management_Plateform/commentaires_archivées.txt", true);
            fileWriter.write(commentaire.getIdComment() + "," + commentaire.getDescription() + "," +
                    commentaire.getCreationDate() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
