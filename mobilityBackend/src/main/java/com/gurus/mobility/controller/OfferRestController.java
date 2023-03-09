package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Destination;
import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.OfferRepository.IOfferRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.OfferService.IOfferService;
import com.gurus.mobility.service.User.IUserService;
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
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }
    @Autowired
    private IUserService iUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private IOfferService offerService;
    @Autowired
    private IOfferRepository offerRepository;




//    http://localhost:8080/espritmobility/api/offers/getOffers
    @GetMapping("/getOffers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

//    http://localhost:8080/espritmobility/api/offers/4
    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Integer id) {
        return offerService.getOfferById(id);
    }


//    http://localhost:8080/espritmobility/api/offers/createOffer
    @PostMapping("/createOffer")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

//    http://localhost:8080/espritmobility/api/offers/10
    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable Integer id, @Valid @RequestBody Offer offerDetails) {
        return offerService.updateOffer(id, offerDetails);
    }

//    http://localhost:8080/espritmobility/api/offers/10
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

//    http://localhost:8080/espritmobility/api/offers/search?title=offre mobilité etudiant Stutgart
    @GetMapping("/search")
    public ResponseEntity<List<Offer>> searchOffers(@RequestParam(value = "title") String title) {
        List<Offer> offers = offerService.getOffersByTitle(title);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

//    http://localhost:8080/espritmobility/api/offers/tri/date
    @GetMapping("/tri/date")
    public ResponseEntity<List<Offer>> trierParDate() {
        List<Offer> offers = offerService.trierParDate();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

//    http://localhost:8080/espritmobility/api/offers/9/archive
    @PostMapping("/{id}/archive")
    public void archiveCandidature(@PathVariable Integer id) {
        offerService.archiveOffer(id);
    }

//    http://localhost:8080/espritmobility/api/offers/export/excel
    @GetMapping("/export/excel")
    public void exportOffersToExcel(HttpServletResponse response) {
        offerService.exportOffersToExcel(response);
    }

//    http://localhost:8080/espritmobility/api/offers/pagination?pageNumber=0&pageSize=10&sortBy=idOffre
    @GetMapping("/pagination")
    public ResponseEntity<Page<Offer>> paginationOffers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Offer> result = offerService.paginationOffers(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }

//    http://localhost:8080/espritmobility/api/offers/dateOffer/2023-01-01
    @GetMapping("/dateOffer/{date}")
    public List<Offer> getOffresDateSuperieur(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return offerService.getOffresDateSuperieur(date);
    }

//    http://localhost:8080/espritmobility/api/offers/statistiques
    @GetMapping("/statistiques")
    public Map<Destination, Long> getNombreOffresParDestination() {
        return offerService.getNombreOffresParDestination();
    }

//    http://localhost:8080/espritmobility/api/offers/pdf
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

//    http://localhost:8080/espritmobility/api/offers/enseignant
    @GetMapping("/enseignant")
    public List<Offer> getOffresEnseignant() {
        return offerService.getOffresEnseignant();
    }

//    http://localhost:8080/espritmobility/api/offers/etudiant
    @GetMapping("/etudiant")
    public List<Offer> getOffresEtudiant() {
        return offerService.getOffresEtudiant();
    }




}


