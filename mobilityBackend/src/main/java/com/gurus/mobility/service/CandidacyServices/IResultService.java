package com.gurus.mobility.service.CandidacyServices;


import com.gurus.mobility.entity.Candidacy.Result;

import java.util.List;

public interface IResultService {
    List<Result> getAllResult();
    Result getResultById(Integer id);
    Result createResult(Result result);
    Result updateResult(Integer id, Result resultDetails);
    void deleteResult(Integer id);
}