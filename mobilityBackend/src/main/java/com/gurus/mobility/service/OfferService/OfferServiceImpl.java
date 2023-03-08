package com.gurus.mobility.service.OfferService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.gurus.mobility.entity.Offer.Destination;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.Offer.Profil;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
@Slf4j
public class OfferServiceImpl implements IOfferService {
    @Autowired
    private IOfferRepository offerRepository;

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Integer id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id " + id));
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }


    @Override
    public Offer updateOffer(Integer id, Offer offerDetails) {
        Offer offer = getOfferById(id);
        offer.setTitle(offerDetails.getTitle());
        offer.setImage(offerDetails.getImage());
        offer.setDateOffre(offerDetails.getDateOffre());
        offer.setNbreCandidats(offerDetails.getNbreCandidats());
        offer.setProfil(offerDetails.getProfil());
        offer.setDestination(offerDetails.getDestination());
        offer.setDuration(offerDetails.getDuration());
        offer.setConditions(offerDetails.getConditions());
        offer.setAdvantages(offerDetails.getAdvantages());


        return offerRepository.save(offer);
    }


    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getOffersByTitle(String title) {
        return offerRepository.findByTitle(title);
    }

    @Override
    public List<Offer> trierParDate() {
        List<Offer> offers = offerRepository.findAll();
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer c1, Offer c2) {
                return c1.getDateOffre().compareTo(c2.getDateOffre());
            }
        });
        return offers;
    }

    @Override
    public void archiveOffer(Integer id) {
        Offer offer = getOfferById(id);
        offerRepository.delete(offer);

        try {
            FileWriter fileWriter = new FileWriter("C:/Code Gurus 2023/International_Mobility_Management_Plateform/offers_archivées.txt", true);
            fileWriter.write(offer.getIdOffre() + "," + offer.getTitle() + "," +
                    offer.getImage() + "," + offer.getNbreCandidats() + "," +
                    offer.getNbreCandidats() + "," +
                    offer.getConditions() + "," +
                    offer.getDestination() + "," +
                    offer.getDuration() + "," +
                    offer.getDateOffre() + "," +
                    offer.getProfil() + "," +
                    offer.getAdvantages() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportOffersToExcel(HttpServletResponse response) {
        try {
            List<Offer> offers = offerRepository.findAll();

            // Création d'un nouveau classeur Excel
            XSSFWorkbook workbook = new XSSFWorkbook();
            // Création d'une nouvelle feuille dans le classeur
            XSSFSheet sheet = workbook.createSheet("Offers");

            // Création d'une ligne pour les titres des colonnes
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Image");
            headerRow.createCell(3).setCellValue("nbreCandidats");
            headerRow.createCell(4).setCellValue("conditions");



            // Remplissage des données des candidatures
            int rowNum = 1;
            for (Offer offer : offers) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(offer.getIdOffre());
                row.createCell(1).setCellValue(offer.getTitle());
                row.createCell(2).setCellValue(offer.getImage());
                row.createCell(3).setCellValue(offer.getNbreCandidats());
                row.createCell(4).setCellValue(offer.getConditions());




            }

            // Configuration de l'en-tête de la réponse HTTP
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"offers.xlsx\"");

            // Écriture du classeur dans le flux de sortie HTTP
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        } }

    @Override
    public Page<Offer> paginationOffers(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return offerRepository.findAll(pageable);
    }

    @Override
    public List<Offer> getOffresDateSuperieur(LocalDate date) {
        return offerRepository.getOffresDateSuperieur(date);
    }

    @Override
    public Map<Destination, Long> getNombreOffresParDestination() {
        List<Offer> offres = offerRepository.findAll();
        return offres.stream().collect(Collectors.groupingBy(Offer::getDestination, Collectors.counting()));
    }

    @Override
    public List<Offer> getOffresEnseignant() {
        return offerRepository.findByProfil(Profil.ENSEIGNANT);
    }

    @Override
    public List<Offer> getOffresEtudiant() {
        return offerRepository.findByProfil(Profil.ETUDIANT);
    }


}