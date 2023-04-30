package com.gurus.mobility.service.CandidacyServices;


import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IResultService {
    List<Result> getAllResult();
    Result getResultById(Integer id);
    Result createResult(Result result);
    Result updateResult(Integer id, Result resultDetails);
    void deleteResult(Integer id);
    void exportResultToExcel(HttpServletResponse response);

    List<Result> findTop10ByOrderByNoteDesc();

    Result findById(Integer id);
    List<Result> findAll();
    Result save(Result result);
    void archiveResult(Integer id);

}