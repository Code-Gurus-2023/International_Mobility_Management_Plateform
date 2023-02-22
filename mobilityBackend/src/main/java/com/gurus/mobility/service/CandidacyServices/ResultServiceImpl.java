package com.gurus.mobility.service.CandidacyServices;

import com.gurus.mobility.entity.Candidacy.Candidacy;
import com.gurus.mobility.entity.Candidacy.Result;
import com.gurus.mobility.repository.Candidacy.ICandidacyRepository;
import com.gurus.mobility.repository.Candidacy.IResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Slf4j
@Service
public class ResultServiceImpl implements IResultService {

    @Autowired
    private IResultRepository resultRepository;

    @Override
    public List<Result> getAllResult() {return resultRepository.findAll();
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
        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }


}