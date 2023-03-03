package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Destination;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import com.gurus.mobility.service.OfferService.IOfferService;
import com.gurus.mobility.service.OfferService.OfferServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private IOfferService offerService;
    @Autowired
    private IOfferRepository offerRepository;


    @GetMapping("/getOffers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Integer id) {
        return offerService.getOfferById(id);
    }

    @PostMapping("/createOffer")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable Integer id, @Valid @RequestBody Offer offerDetails) {
        return offerService.updateOffer(id, offerDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Offer>> searchOffers(@RequestParam(value = "title") String title) {
        List<Offer> offers = offerService.getOffersByTitle(title);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/tri/date")
    public ResponseEntity<List<Offer>> trierParDate() {
        List<Offer> offers = offerService.trierParDate();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        offerService.archiveOffer(id);
    }

    @GetMapping("/export/excel")
    public void exportOffersToExcel(HttpServletResponse response) {
        offerService.exportOffersToExcel(response);
    }

    @GetMapping
    public ResponseEntity<Page<Offer>> paginationOffers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Offer> result = offerService.paginationOffers(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/dateOffer/{date}")
    public List<Offer> getOffresDateSuperieur(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return offerService.getOffresDateSuperieur(date);
    }

    @GetMapping("/statistiques")
    public Map<Destination, Long> getNombreOffresParDestination() {
        return offerService.getNombreOffresParDestination();
    }

//    @GetMapping(value = "/{id}/pdf", produces = "application/pdf")
//    public ResponseEntity<byte[]> generateOfferPdf(@PathVariable Integer id) throws Exception {
//        Offer offer = offerService.getOfferById(id);
//        if (offer == null) {
//            System.out.println("Offer not found with id " + id);
//        }
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, outputStream);
//
//        document.open();
//
//        Paragraph title = new Paragraph("Offer Details");
//        title.setAlignment(Element.ALIGN_CENTER);
//        document.add(title);
//
//        document.add(new Paragraph("Id: " + offer.getIdOffre()));
//        document.add(new Paragraph("Title: " + offer.getTitle()));
//        document.add(new Paragraph("Image: " + offer.getImage()));
//        document.add(new Paragraph("Conditions: " + offer.getConditions()));
//        document.add(new Paragraph("Duration: " + offer.getDuration()));
//        document.add(new Paragraph("Profil: " + offer.getProfil()));
//        document.add(new Paragraph("NombreCandidats: " + offer.getNbreCandidats()));
//        document.add(new Paragraph("DateOffre: " + offer.getDateOffre()));
//        document.add(new Paragraph("Avantages: " + offer.getAdvantages()));
//
//        document.close();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.setContentDispositionFormData("attachment", "offer-" + offer.getIdOffre() + ".pdf");
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
//        return responseEntity;
//    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePDF() throws DocumentException, IOException {
        List<Offer> offres = offerService.getAllOffers();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();


        Image logo = Image.getInstance("C:/Code Gurus 2023/International_Mobility_Management_Plateform/logo.png");
        logo.setAlignment(Element.ALIGN_CENTER);
        logo.scaleToFit(200f, 200f);
        document.add(logo);

        // Ajouter un titre
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.RED);
        Paragraph titre = new Paragraph("Liste Des Offres", font);
        titre.setAlignment(Element.ALIGN_CENTER);
        document.add(titre);


        // Ajouter un tableau
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        // Ajouter des en-têtes de colonnes
        PdfPCell cell = new PdfPCell(new Phrase("Title"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Conditions"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Date de l'offre"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre de candidats"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Destination"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        table.addCell(cell);
        // Ajouter les données
        for (Offer offre : offres) {
            table.addCell(offre.getTitle());
            table.addCell(offre.getConditions());
            table.addCell(offre.getDateOffre().toString());
            table.addCell(offre.getNbreCandidats().toString());
            table.addCell(offre.getDestination().toString());
        }
        document.add(table);
        document.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("offres.pdf").build());
        headers.setContentLength(baos.size());
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}


