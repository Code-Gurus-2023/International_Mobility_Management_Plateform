package com.gurus.mobility.service.CandidacyServices;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.DomainCandidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.repository.Candidacy.IResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service

public class ResultServiceImpl implements IResultService {

    @Autowired
    private IResultRepository resultRepository;


    @Autowired
    public ResultServiceImpl(IResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }


    @Override
    public List<Result> getAllResult() {
        return resultRepository.findAll();
    }

    @Override
    public Result getResultById(Integer id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Result not found with id " + id));
    }

    @Override
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(Integer id, Result resultDetails) {
        Result result = getResultById(id);
        result.setPl(resultDetails.getPl());
        result.setDataMining(resultDetails.getDataMining());
        result.setSpringBoot(resultDetails.getSpringBoot());
        result.setAngular(resultDetails.getAngular());
        result.setDotnet(resultDetails.getDotnet());
        result.setEnglish(resultDetails.getEnglish());
        result.setFrench(resultDetails.getFrench());
        result.setMath(resultDetails.getMath());
        result.setDate(resultDetails.getDate());
        result.setGeneralAverage(resultDetails.getGeneralAverage());
        result.setStudentSpeciality(resultDetails.getStudentSpeciality());
        result.setScore(resultDetails.getScore());

        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }

    @Override
    public void exportResultToExcel(HttpServletResponse response) {
        try {
            List<Result> results = resultRepository.findAll();

// Création d'un nouveau classeur Excel
            XSSFWorkbook workbook = new XSSFWorkbook();

// Création d'une nouvelle feuille dans le classeur
            XSSFSheet sheet = workbook.createSheet("Results");

// Création d'une ligne pour les titres des colonnes
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("idResult");
            headerRow.createCell(1).setCellValue("pl");
            headerRow.createCell(2).setCellValue("dataMining");
            headerRow.createCell(3).setCellValue("springBoot");
            headerRow.createCell(4).setCellValue("angular");
            headerRow.createCell(5).setCellValue("dotnet");
            headerRow.createCell(6).setCellValue("english");
            headerRow.createCell(7).setCellValue("french");
            headerRow.createCell(8).setCellValue("math");
            headerRow.createCell(9).setCellValue("generalAverage");
            headerRow.createCell(10).setCellValue("score");

// Remplissage des données des candidatures
            int rowNum = 1;
            for (Result result : results) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(result.getIdResult());
                row.createCell(1).setCellValue(result.getPl());
                row.createCell(2).setCellValue(result.getDataMining());
                row.createCell(3).setCellValue(result.getSpringBoot());
                row.createCell(4).setCellValue(result.getAngular());
                row.createCell(5).setCellValue(result.getDotnet());
                row.createCell(6).setCellValue(result.getEnglish());
                row.createCell(7).setCellValue(result.getFrench());
                row.createCell(8).setCellValue(result.getMath());
                row.createCell(9).setCellValue(result.getGeneralAverage());
                row.createCell(10).setCellValue(result.getScore());

            }
// Configuration de l'en-tête de la réponse HTTP
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"results.csv\"");

            // Écriture du classeur dans le flux de sortie HTTP
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Result> findTop10ByOrderByNoteDesc() {
        Sort sort = Sort.by("generalAverage").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        return resultRepository.findAll(pageable).getContent();
    }

    @Override
    public Result save(Result result) {
        result.setScore(result.calculateScore());
        return resultRepository.save(result);
    }

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result findById(Integer id) {
        return resultRepository.findById(id).orElse(null);
    }

    @Override
    public void archiveResult(Integer id) {
        Result result = getResultById(id);
        resultRepository.delete(result);

        try {
            FileWriter fileWriter = new FileWriter("C:/Spring Boot/Resultats_archivées.txt", true);
            fileWriter.write(result.getIdResult() + "," + result.getPl() + "," +
                    result.getAngular() + "," + result.getDotnet() + "," +
                    result.getScore() + result.getGeneralAverage() + "," + result.getDate() + "," + result.getDataMining() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

